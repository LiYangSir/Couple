package com.quqiququai.dao;

import com.quqiququai.po.Gift;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long>, JpaSpecificationExecutor<Gift> {

    List<Gift> findByAcceptUser_IdAndAuditFlagFalseAndPublishFlagTrue(Long id);

    List<Gift> findByAcceptUser_IdAndAuditFlagTrue(Long id);

    List<Gift> findByAcceptUser_IdAndAchieveFlagTrueAndAuditFlagFalseAndPublishFlagTrue(Long id);

    List<Gift> findByPublishUser_IdAndAchieveFlagTrueAndAuditFlagFalse(Long id);

    List<Gift> findByPublishUser_IdAndAchieveFlagTrueAndAuditFlagTrue(Long id);

    List<Gift> findByPublishUser_IdAndPublishFlagTrue(Long id);

    List<Gift> findByPublishFlagTrue();

    @Query("select g from Gift g where g.publishFlag = true and g.achieveFlag = false")
    List<Gift> findTop(Pageable pageable);
}
