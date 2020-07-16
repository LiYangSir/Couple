package com.quqiququai.web;

import com.quqiququai.po.User;
import com.quqiququai.po.WebBasic;
import com.quqiququai.service.UserService;
import com.quqiququai.service.WebBasicService;
import com.quqiququai.utils.AddressSplit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.List;

@Controller
public class AboutController {

    @Autowired
    private UserService userService;

    @Autowired
    private WebBasicService webBasicService;

    @GetMapping("/about")
    public String about(Model model) {
        List<User> users = userService.getUsers();
        for (User user : users) {
            user.setPassword(null);
            user.setId(null);
            user.setIntegrals(null);
            user.setUserName(null);
        }
        WebBasic webBasic = webBasicService.getWebBasic(1L);
//        webBasic.setBottomUrls(AddressSplit.addressSplit(webBasic.getUrls()));
        webBasic.setMissTime((new Date().getTime() - webBasic.getMissDate().getTime()) / ( 24 * 60 * 60 * 1000));
        model.addAttribute("users", users);
        model.addAttribute("webBasic", webBasic);
        return "about";
    }
}
