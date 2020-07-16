package com.quqiququai.service;

import com.quqiququai.po.Gift;
import com.quqiququai.vo.GiftQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftService {

    Gift getGift(Long id);

    Page<Gift> listGift(Pageable pageable, GiftQuery gift);

    Gift saveGift(Gift gift);

    List<Gift> listGiftTop(Integer size);

    Gift updateGift(Long id, Gift gift);

    boolean deleteGift(Long id);

    List<Gift> getGiftAchieveAndAudit(Long id);

    List<Gift> getGiftAchieveNotAudit(Long id);

    List<Gift> listAcceptUserGift(Long id);

    List<Gift> listGiftAcceptUserAuditing(Long id);

    List<Gift> listGiftAcceptUserAndAudit(Long id);

    List<Gift> listAllGift();

    List<Gift> listGiftByPublishUser(Long id);
}
