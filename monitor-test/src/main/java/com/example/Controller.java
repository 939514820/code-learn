package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("health")
    public String getName() {
        return "";
    }

    @GetMapping("getbyUid")
    public String getName(String uid) {
        return uid;
    }

    @GetMapping("getbyUid1")
    public String getNameNew(String uid) {
        return uid;
    }
    @RequestMapping(value = "/index")
    public Object index() {
        Integer integer = Integer.valueOf("123s");
        return " index of springboot2-prometheus.";
    }
}
