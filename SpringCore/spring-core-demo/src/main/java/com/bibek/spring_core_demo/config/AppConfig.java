package com.bibek.spring_core_demo.config;

import com.bibek.spring_core_demo.ObjTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjTest objTest(){
        return new ObjTest();
    }
}