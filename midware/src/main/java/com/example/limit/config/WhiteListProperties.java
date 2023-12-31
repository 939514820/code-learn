package com.example.limit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("bugstack.whitelist")
public class WhiteListProperties {

    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
