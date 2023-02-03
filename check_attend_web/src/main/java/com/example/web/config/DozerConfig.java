package com.example.web.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lincheon
 * @since 2022/1/16 15:31
 **/
@Configuration
public class DozerConfig {

    @Bean
    public DozerBeanMapper getDozerBeanMapper(){
        return new DozerBeanMapper();
    }
}
