package com.quqiququai.web.admin;

import com.quqiququai.po.Gift;
import com.quqiququai.po.Picture;
import com.quqiququai.po.User;
import com.quqiququai.service.*;
import com.quqiququai.vo.GiftQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    @GetMapping("/gift")
    public String showGift(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Gift> hasGet = giftService.listGiftAcceptUserAndAudit(user.getId());
        List<Gift> auditing = giftService.listGiftAcceptUserAuditing(user.getId());
        model.addAttribute("hadGet", hasGet);
        model.addAttribute("auditing", auditing);
        return "admin/gift";
    }

    @GetMapping("/publish")
    public String gifts(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        GiftQuery gift, Model model, HttpSession session) {
        gift.setUserId(((User) session.getAttribute("user")).getId());
        model.addAttribute("keywords", keywordService.listKeyword());
        model.addAttribute("page", giftService.listGift(pageable, gift));
        return "admin/publish";
    }

    @PostMapping("/publish/search")
    public String giftSearch(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             GiftQuery gift, Model model, HttpSession session) {
        gift.setUserId(((User) session.getAttribute("user")).getId());
        model.addAttribute("page", giftService.listGift(pageable, gift));
        return "admin/publish :: giftList";
    }

    @GetMapping("/publish/input")
    public String input(Model model) {
        Gift gift = new Gift();
        gift.setFirstPicUrl("https://semantic-ui.com/images/wireframe/image.png");
        model.addAttribute("gift", gift);
        model.addAttribute("keywords", keywordService.listKeyword());
        return "admin/publish-input";
    }

    @GetMapping("/publish/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("gift", giftService.getGift(id));
        model.addAttribute("keywords", keywordService.listKeyword());
        return "admin/publish-input";
    }

    @GetMapping("/publish/{id}/delete")
    public String deleteGift(@PathVariable Long id, RedirectAttributes attributes) {
        boolean b = giftService.deleteGift(id);
        attributes.addFlashAttribute("message", b ? "删除成功" : "删除失败");
        return "redirect:/admin/publish";
    }

    @PostMapping("/publish")
    public String post(Gift gift, HttpSession session, RedirectAttributes attributes) {
        User user = (User) session.getAttribute("user");
        gift.setPublishUser(user);
        gift.setState("未完成...");
        List<Picture> giftPicUrl = pictureService.getGiftPicUrl();
        gift.setAcceptUser(userService.getAdverseUser(user.getId()));
        gift.setKeyword(keywordService.getType(gift.getKeyword().getName()));
        Gift g;
        if (gift.getId() == null) {
            if (giftPicUrl.size() == 0){
                Picture picture = new Picture();
                picture.setPicUrl("https://semantic-ui.com/images/wireframe/image.png");
                giftPicUrl.add(picture);
            }
            gift.setGiftPictures(giftPicUrl);
            g = giftService.saveGift(gift);

            for (Picture picture : giftPicUrl) {
                picture.setGift(g);
                pictureService.savePic(picture);
            }
        } else {
            List<Picture> old = giftService.getGift(gift.getId()).getGiftPictures();
            if (giftPicUrl.size() == 0 || old.size() == 0){
                Picture picture = new Picture();
                picture.setPicUrl("https://semantic-ui.com/images/wireframe/image.png");
                giftPicUrl.add(picture);
            }
            for (Picture picture : giftPicUrl) {
                picture.setGift(gift);
                pictureService.savePic(picture);
            }
            giftPicUrl.addAll(old);
            gift.setGiftPictures(giftPicUrl);
            g = giftService.updateGift(gift.getId(), gift);
        }
        if (g == null)
            attributes.addFlashAttribute("message", "操作失败");
        else{
            Picture topPicByGiftId = pictureService.getTopPicByGiftId(g.getId());
            g.setFirstPicUrl(topPicByGiftId.getPicUrl());
            Gift gift1 = giftService.saveGift(g);
            if (gift1 == null)
                attributes.addFlashAttribute("message", "操作失败");
            else
                attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/publish";
    }
}
