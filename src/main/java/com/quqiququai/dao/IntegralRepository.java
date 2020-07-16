package com.quqiququai.dao;

import com.quqiququai.po.Integral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegralRepository extends JpaRepository<Integral, Long> {
    Integral findFirstByUser_IdOrderByCreateTimeDesc(Long id);
}