package com.example.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DruidConfig {
    @Bean
    ServletRegistrationBean regisDruid() {
        //固定写法，配置访问路径
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置登录信息，固定写法
        HashMap<String, String> initParams = new HashMap<>();
        //账号和密码的key是固定的
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");

        //允许谁可以访问
        initParams.put("allow", "");

        //禁止谁访问 initParams.put("huangcc","192.168.3.12");
        //设置初始化参数
        bean.setInitParameters(initParams);
        return bean;
    }

}
