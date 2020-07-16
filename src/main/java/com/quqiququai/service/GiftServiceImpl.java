package com.quqiququai.service;

import com.quqiququai.NotFoundException;
import com.quqiququai.dao.GiftRepository;
import com.quqiququai.po.Gift;
import com.quqiququai.utils.MyBeanUtils;
import com.quqiququai.vo.GiftQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftRepository giftRepository;

    @Override
    public Gift getGift(Long id) {
        return giftRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Gift> listGift(Pageable pageable, GiftQuery gift) {
        return giftRepository.findAll((Specification<Gift>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("achieveFlag"), false));
            if(!"".equals(gift.getTask()) && gift.getTask() != null)
                predicates.add(criteriaBuilder.like(root.get("task"), "%" + gift.getTask() + "%"));
            if (!"".equals(gift.getKeyword()) && gift.getKeyword() != null)
                predicates.add(criteriaBuilder.like(root.get("keyword").get("name"), "%" + gift.getKeyword() + "%"));
            if (gift.isPublished())
                predicates.add(criteriaBuilder.equal(root.get("publishFlag"), gift.isPublished()));
            if (gift.getUserId() != null)
                predicates.add(criteriaBuilder.equal(root.get("publishUser").get("id"), gift.getUserId()));

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return null;
        }, pageable);
    }


    @Transactional
    @Override
    public Gift saveGift(Gift gift) {
        gift.setCreateTime(new Date());
        gift.setUpdateTime(new Date());

        return giftRepository.save(gift);
    }

    @Transactional
    @Override
    public Gift updateGift(Long id, Gift gift) {
        Optional<Gift> byId = giftRepository.findById(id);
        if (!byId.isPresent()){
            throw new NotFoundException("任务不存在");
        }
        Gift one = byId.get();
        BeanUtils.copyProperties(gift, one, MyBeanUtils.getNullPropertyNames(gift));
        one.setUpdateTime(new Date());

        return giftRepository.save(one);
    }

    @Override
    public List<Gift> listGiftTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return giftRepository.findTop(pageable);
    }

    @Override
    public List<Gift> listGiftByPublishUser(Long id) {
        return giftRepository.findByPublishUser_IdAndPublishFlagTrue(id);
    }

    @Transactional
    @Override
    public boolean deleteGift(Long id) {

        if (giftRepository.findById(id).isPresent()) {
            giftRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    /**
     * 获得所有礼物，（我的审核，已经完成，但是已审核）
     * @param id：发布者的id
     * @return : 礼物列表
     */

    @Override
    public List<Gift> getGiftAchieveAndAudit(Long id) {
        return giftRepository.findByPublishUser_IdAndAchieveFlagTrueAndAuditFlagTrue(id);
    }

    /**
     * 获得所有礼物，（我的审核，已经完成，但是未审核）
     * @param id：发布者的id
     * @return : 礼物列表
     */

    @Override
    public List<Gift> getGiftAchieveNotAudit(Long id) {
        return giftRepository.findByPublishUser_IdAndAchieveFlagTrueAndAuditFlagFalse(id);
    }

    /**
     *           我的礼物，已经获得的
     * @param id：对方id
     * @return : 礼物列表
     */

    @Override
    public List<Gift> listGiftAcceptUserAndAudit(Long id) {
        return giftRepository.findByAcceptUser_IdAndAuditFlagTrue(id);
    }

    /**
     *           我的礼物，已完成未审核的
     * @param id：对方id
     * @return : 礼物列表
     */

    @Override
    public List<Gift> listGiftAcceptUserAuditing(Long id) {
        return giftRepository.findByAcceptUser_IdAndAchieveFlagTrueAndAuditFlagFalseAndPublishFlagTrue(id);
    }

    /**
     *           我的礼物，未完成的
     * @param id：对方id
     * @return : 礼物列表
     */

    /* 未引入发布状态的变化情况 */
    @Override
    public List<Gift> listAcceptUserGift(Long id) {
        return giftRepository.findByAcceptUser_IdAndAuditFlagFalseAndPublishFlagTrue(id);
    }

    @Override
    public List<Gift> listAllGift() {
        return giftRepository.findByPublishFlagTrue();
    }
}
