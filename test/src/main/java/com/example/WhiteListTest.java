package com.example;

import com.example.annotation.WhiteList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhiteListTest {
    @GetMapping("getWhite")
    @WhiteList(key = "name", returnJson = "{\"msg\":\"\",\"code\":1}")
    public String test(@RequestParam String name) {
        return "success";
    }
}
