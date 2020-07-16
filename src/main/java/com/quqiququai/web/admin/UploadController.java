package com.quqiququai.web.admin;

import com.quqiququai.po.Picture;
import com.quqiququai.po.User;
import com.quqiququai.service.GiftService;
import com.quqiququai.service.PictureService;
import com.quqiququai.utils.SaveAndGetPicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private GiftService giftService;

    @Autowired
    private PictureService pictureService;


    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("uploadImage") MultipartFile file, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String url = SaveAndGetPicUtils.saveGiftPic(file, uploadPath + user.getUserName());
        String replace = url.replace('\\', '/');
        Picture picture = new Picture();
        picture.setPicUrl(replace);
        pictureService.savePic(picture);
        return "1";
    }

    @PostMapping("/publish/upload/{id}")
    public String getPicList(Model model, @PathVariable Long id) {
        List<Picture> giftPicUrl = pictureService.getGiftPicUrl();
        if (id == 0) {
            model.addAttribute("picUrls", giftPicUrl);
        }else {
            List<Picture> old = pictureService.getPicByGift(id);
            model.addAttribute("picUrls", giftPicUrl);
            model.addAttribute("oldPic", old);
        }
        return "admin/publish-input :: picList";
    }

    @PostMapping("/up/delete/{id}/{giftId}")
    public String deletePic(Model model, @PathVariable Long id, @PathVariable Long giftId) {
        pictureService.deletePic(id);
        List<Picture> giftPicUrl = pictureService.getGiftPicUrl();
        List<Picture> old = pictureService.getPicByGift(giftId);
        model.addAttribute("picUrls", giftPicUrl);
        model.addAttribute("oldPic", old);
        return "admin/publish-input :: picList";
    }

    @PostMapping("/up/delete/{id}/")
    public String deletePic2(Model model, @PathVariable Long id) {
        pictureService.deletePic(id);
        List<Picture> giftPicUrl = pictureService.getGiftPicUrl();
        model.addAttribute("picUrls", giftPicUrl);
        return "admin/publish-input :: picList";
    }
}
