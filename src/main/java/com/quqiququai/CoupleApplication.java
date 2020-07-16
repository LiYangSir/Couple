package com.quqiququai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoupleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoupleApplication.class, args);
    }

}
