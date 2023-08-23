package com.example.config;

import com.example.jointpoint.DoRateLimiterPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterAutoConfigure {
    @Bean
    @ConditionalOnMissingBean
    public DoRateLimiterPoint doRateLimiterPoint() {
        return new DoRateLimiterPoint();
    }
}
