package com.example.config;

import com.example.DoJointPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorAutoConfigure {
    /**
     * @ConditionalOnMissingBean，它是修饰bean的一个注解，主要实现的是，当你的bean被注册之后，如果而注册相同类型的bean，就不会成功，它会保证你的bean只有一个，即你的实例只有一个。
     *
     * 如果不加@ConditionalOnMissingBean，当你注册多个相同的bean时，会出现异常，以此来告诉开发人员。
     * ————————————————
     * 版权声明：本文为CSDN博主「CBAiotAigc」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/wtl1992/article/details/122210599
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DoJointPoint point(){
        return new DoJointPoint();
    }

}

