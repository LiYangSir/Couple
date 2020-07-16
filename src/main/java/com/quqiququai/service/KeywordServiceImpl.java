package com.quqiququai.service;

import com.quqiququai.dao.KeywordRepository;
import com.quqiququai.po.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public List<Keyword> listKeyword() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword getType(Long id) {
        return keywordRepository.findById(id).orElse(null);
    }

    @Transactional
    public Keyword saveType(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    @Transactional
    @Override
    public Keyword getType(String name) {
        Keyword byName = keywordRepository.findByName(name);
        if (byName == null){
            Keyword k = new Keyword();
            k.setName(name);
            return saveType(k);
        }
        return byName;
    }
}
