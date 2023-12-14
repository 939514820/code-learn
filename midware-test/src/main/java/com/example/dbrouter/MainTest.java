package com.example.dbrouter;

import com.example.limit.annotation.DoRateLimiter;
import com.example.limit.annotation.WhiteList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainTest {
    @GetMapping("getWhite")
    @WhiteList(key = "name", returnJson = "{\"msg\":\"\",\"code\":1}")
    public Object test(@RequestParam String name) {
        return "success";
    }

    @GetMapping("getlimit")
    @DoRateLimiter(limit = 1, defaultReturn = "{\"msg\":\"too many request\",\"code\":1}")
    public Object limit(@RequestParam String name) {
        return "success";
    }
}
