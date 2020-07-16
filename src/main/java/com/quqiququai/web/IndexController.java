package com.quqiququai.web;

import com.quqiququai.po.Gift;
import com.quqiququai.po.User;
import com.quqiququai.po.WebBasic;
import com.quqiququai.service.GiftService;
import com.quqiququai.service.UserService;
import com.quqiququai.service.WebBasicService;
import com.quqiququai.vo.FooterQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private WebBasicService webBasicService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
        List<Gift> gifts = giftService.listAllGift();
        model.addAttribute("gifts", gifts);
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Gift gift = giftService.getGift(id);
        List<Gift> gifts = giftService.listAllGift();
        if(gift == null){
            model.addAttribute("message", "任务不存在");
            return "index";
        }
        model.addAttribute("gift", gift);
        model.addAttribute("gifts", gifts);
        return "detail";
    }



    @GetMapping("/footer")
    public String footer(Model model) {
        FooterQuery footerQuery = new FooterQuery();
        List<User> users = userService.getUsers();
        for (User user : users) {
            user.setPassword(null);
        }
        WebBasic webBasic = webBasicService.getWebBasic(1L);
        footerQuery.setDescription(webBasic.getDescription());
        footerQuery.setGiftList(giftService.listGiftTop(3));
        footerQuery.setUsers(users);
        model.addAttribute("footerQuery", footerQuery);
        return "_fragments :: footerList";
    }

    @GetMapping("/user/{id}")
    public String selfGift(Model model, @PathVariable Long id) {
        List<Gift> gifts = giftService.listGiftByPublishUser(id);
        model.addAttribute("gifts", gifts);
        return "index";
    }
}
