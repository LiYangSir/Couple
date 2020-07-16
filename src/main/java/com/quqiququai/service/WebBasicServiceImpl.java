package com.quqiququai.service;

import com.quqiququai.dao.WebBasicRepository;
import com.quqiququai.po.WebBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebBasicServiceImpl implements WebBasicService{

    @Autowired
    private WebBasicRepository webBasicRepository;

    @Override
    public WebBasic getWebBasic(Long id) {
        return webBasicRepository.findById(id).orElse(null);
    }
}
