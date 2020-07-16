package com.quqiququai.web.admin;

import com.quqiququai.po.Picture;
import com.quqiququai.po.User;
import com.quqiququai.service.UserService;
import com.quqiququai.utils.MD5Utils;
import com.quqiququai.utils.SaveAndGetPicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class SettingController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Map<String, Object> uploadAvatar(@RequestParam("uploadImage") MultipartFile file, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String url = SaveAndGetPicUtils.saveGiftPic(file, uploadPath + user.getUserName() + "/avatar");
        String replace = url.replace('\\', '/');
        User userById = userService.getUserById(user.getId());
        userById.setAvatar(replace);
        userService.updateUser(userById);
        userById.setPassword(null);
        session.setAttribute("user", userById);
        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("url", replace);
        return result;
    }

    @GetMapping("/setting")
    public String setting(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        User userById = userService.getUserById(user.getId());
        model.addAttribute("setting", userById);
        return "admin/setting";
    }

    @PostMapping("/setting")
    public String post(User user, RedirectAttributes attributes, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        User userById = userService.getUserById(sessionUser.getId());
        if (userById == null) {
            attributes.addFlashAttribute("message", "用户不存在");
            return "redirect:/admin/setting";
        }
        User user1 = userService.updateUser(userById.getId(), user);
        attributes.addFlashAttribute("setting", user1);
        attributes.addFlashAttribute("message", "修改成功");
        userById.setPassword(null);
        session.setAttribute("user", userById);

        return "redirect:/admin/setting";
    }

    @PostMapping("/setting/password")
    public String password(@RequestParam String oldPassword, @RequestParam String newPassword, Model model, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        User user = userService.getUserById(sessionUser.getId());
        String oldMD5Password = MD5Utils.code(oldPassword);
        if (user.getPassword().equals(oldMD5Password)) {
            user.setPassword(MD5Utils.code(newPassword));
            userService.updateUser(user);
            model.addAttribute("message", "密码修改成功");
            model.addAttribute("setting", user);
        }else{
            model.addAttribute("setting", user);
            model.addAttribute("message", "密码错误");
        }
        return "admin/setting";
    }
}
