package com.quqiququai.web.admin;

import com.quqiququai.po.Gift;
import com.quqiququai.po.User;
import com.quqiququai.service.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SelfIndexController {

    @Autowired
    private GiftService giftService;

    @GetMapping("/index")
    public String detail(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Gift> gifts = giftService.listAcceptUserGift(user.getId());
        model.addAttribute("gifts", gifts);
        return "admin/self-index";
    }

}
