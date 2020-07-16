package com.quqiququai.web.admin;

import com.quqiququai.po.Gift;
import com.quqiququai.po.Integral;
import com.quqiququai.po.User;
import com.quqiququai.service.GiftService;
import com.quqiququai.service.IntegralService;
import com.quqiququai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DetailController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private IntegralService integralService;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        Gift gift = giftService.getGift(id);
        if (gift == null || gift.isAuditFlag() || !gift.isPublishFlag()){
            model.addAttribute("message", "任务未发布，不存在或已完成");
            return "admin/self-index";
        }
        User user = (User) session.getAttribute("user");
        List<Gift> gifts = giftService.listAcceptUserGift(user.getId());
        model.addAttribute("gift", gift);
        model.addAttribute("gifts", gifts);
        return "admin/detail";
    }

    @GetMapping("/detail/{id}/submit")
    public String submit(@PathVariable Long id, RedirectAttributes attributes, HttpSession session) {
        Gift gift = giftService.getGift(id);
        if (gift == null) {
            attributes.addFlashAttribute("message", "任务不存在");
            return "redirect:/admin/self-index";
        }
        User user = (User) session.getAttribute("user");
        int currentIntegral = integralService.getCurrentIntegral(user.getId()).getCurrentIntegral();
        if (gift.getIntegral() > currentIntegral){
            attributes.addFlashAttribute("message", "积分不足");
            return "redirect:/admin/detail/" + id;
        }
        Integral newIntegral = new Integral();
        newIntegral.setAddOrDecFlag(false);
        newIntegral.setAddOrDecNumber(gift.getIntegral());
        newIntegral.setCreateTime(new Date());
        newIntegral.setCurrentIntegral(currentIntegral - gift.getIntegral());
        newIntegral.setUser(user);
        Integral integral = integralService.saveIntegral(newIntegral);
        if (integral == null){
            attributes.addFlashAttribute("message", "提交失败");
            return "redirect:/admin/index";
        }
        gift.setAchieveFlag(true);
        gift.setState("审核过程中...");
        gift.setUpdateTime(new Date());
        giftService.saveGift(gift);
        attributes.addFlashAttribute("message", "成功提交审核");
        return "redirect:/admin/index";
    }
}
