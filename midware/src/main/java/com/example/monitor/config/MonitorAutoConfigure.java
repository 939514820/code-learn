package com.example.monitor.config;

import com.example.monitor.jointpoint.TimeCountPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorAutoConfigure {
    /**
     * @ConditionalOnMissingBean，它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个。
     */
    @Bean
    @ConditionalOnMissingBean
    public TimeCountPoint point(){
        return new TimeCountPoint();
    }

}

