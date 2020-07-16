package com.quqiququai.dao;

import com.quqiququai.po.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Keyword findByName(String name);
}
