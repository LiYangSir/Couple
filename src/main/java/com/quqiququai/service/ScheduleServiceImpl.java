package com.quqiququai.service;

import com.quqiququai.dao.IntegralRepository;
import com.quqiququai.dao.UserRepository;
import com.quqiququai.po.Integral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private IntegralRepository integralRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 秒，分，时，日， 月， 周
     */
    @Transactional
    @Scheduled(cron = "0 0 0 * * 1-5")
    @Override
    public void integralIdentity() {
        add(1);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * 6-7")
    @Override
    public void integralIdentitySun() {
        add(2);
    }

    @Transactional
    @Override
    public void add(Integer integral){
        Integral user1 = integralRepository.findFirstByUser_IdOrderByCreateTimeDesc(1L);
        Integral user2 = integralRepository.findFirstByUser_IdOrderByCreateTimeDesc(2L);
        Integral integral1 = new Integral();
        Integral integral2 = new Integral();
        integral1.setAddOrDecFlag(true);
        integral1.setAddOrDecNumber(integral);
        integral1.setCreateTime(new Date());
        integral1.setCurrentIntegral(user1.getCurrentIntegral() + integral);
        integral1.setUser(userRepository.findById(1L).orElse(null));

        integral2.setAddOrDecFlag(true);
        integral2.setAddOrDecNumber(integral);
        integral2.setCreateTime(new Date());
        integral2.setCurrentIntegral(user2.getCurrentIntegral() + integral);
        integral2.setUser(userRepository.findById(2L).orElse(null));

        integralRepository.save(integral1);
        integralRepository.save(integral2);
    }
}
