package com.quqiququai.service;

import com.quqiququai.dao.IntegralRepository;
import com.quqiququai.po.Gift;
import com.quqiququai.po.Integral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralRepository integralRepository;

    public Integral getCurrentIntegral(Long id){
        return integralRepository.findFirstByUser_IdOrderByCreateTimeDesc(id);
    }

    @Transactional
    @Override
    public Integral saveIntegral(Integral integral) {
        return integralRepository.save(integral);
    }
}
