package com.quqiququai.service;

import com.quqiququai.po.Gift;
import com.quqiququai.po.Integral;

public interface IntegralService {

    Integral getCurrentIntegral(Long id);

    Integral saveIntegral(Integral integral);
}
