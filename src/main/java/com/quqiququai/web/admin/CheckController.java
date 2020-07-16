package com.quqiququai.web.admin;

import com.quqiququai.NotFoundException;
import com.quqiququai.po.Gift;
import com.quqiququai.po.Integral;
import com.quqiququai.po.User;
import com.quqiququai.service.GiftService;
import com.quqiququai.service.IntegralService;
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
public class CheckController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private IntegralService integralService;

    @GetMapping("/check")
    public String checkGift(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Gift> giftAchieveAndAudit = giftService.getGiftAchieveAndAudit(user.getId());
        List<Gift> giftAchieveNotAudit = giftService.getGiftAchieveNotAudit(user.getId());
        model.addAttribute("audited", giftAchieveAndAudit);
        model.addAttribute("auditing", giftAchieveNotAudit);
        return "admin/check";
    }

    @GetMapping("/check/{id}/approve")
    public String approve(RedirectAttributes attributes, @PathVariable Long id) {
        Gift gift = giftService.getGift(id);
        String message = "";
        if (gift == null)
            message = "任务未找到";
        else if (gift.isAuditFlag())
            message = "任务已审核";
        else if (!gift.isAchieveFlag())
            message = "任务未完成";
        else if (!gift.isPublishFlag())
            message = "任务尚未发布";
        if (!"".equals(message)){
            attributes.addFlashAttribute("message", message);
            return "redirect:/admin/check";
        }
        gift.setAuditTime(new Date());
        gift.setUpdateTime(new Date());
        gift.setAuditFlag(true);
        gift.setState("审核通过");
        giftService.saveGift(gift);
        attributes.addFlashAttribute("message", "审核通过");
        return "redirect:/admin/check";
    }

    @GetMapping("/check/{id}/decline")
    public String decline(RedirectAttributes attributes, @PathVariable Long id, HttpSession session) {
        Gift gift = giftService.getGift(id);
        String message = "";
        if (gift == null)
            message = "任务未找到";
        else if (gift.isAuditFlag())
            message = "任务已审核";
        else if (!gift.isAchieveFlag())
            message = "任务未完成";
        else if (!gift.isPublishFlag())
            message = "任务尚未发布";
        if (!"".equals(message)){
            attributes.addFlashAttribute("message", message);
            return "redirect:/admin/check";
        }
        gift.setUpdateTime(new Date());
        gift.setAchieveFlag(false);
        gift.setState("审核被拒绝");
        giftService.saveGift(gift);
        /* 剩余积分操作完成 */
        User user = (User) session.getAttribute("user");
        Integral currentIntegral = integralService.getCurrentIntegral(user.getId());
        Integral integral = new Integral();
        integral.setUser(user);
        integral.setCurrentIntegral(currentIntegral.getCurrentIntegral() + gift.getIntegral());
        integral.setCreateTime(new Date());
        integral.setAddOrDecNumber(gift.getIntegral());
        integral.setAddOrDecFlag(true);
        integralService.saveIntegral(integral);
        attributes.addFlashAttribute("message", "审核拒绝");
        return "redirect:/admin/check";
    }
}
