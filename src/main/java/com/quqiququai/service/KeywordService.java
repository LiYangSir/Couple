package com.quqiququai.service;

import com.quqiququai.po.Keyword;

import java.util.List;
import java.util.PrimitiveIterator;

public interface KeywordService {

    List<Keyword> listKeyword();

    Keyword getType(Long id);

    Keyword getType(String name);

}
