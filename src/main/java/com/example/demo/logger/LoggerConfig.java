package com.example.demo.logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    LoggerInterceptor loggerInterceptorBean() {
        return new LoggerInterceptor();
    }
}
