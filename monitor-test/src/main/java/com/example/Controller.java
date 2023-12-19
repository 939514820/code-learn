package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("getbyUid")
    public String getName(String uid) {
        return uid;
    }

    @GetMapping("getbyUid1")
    public String getNameNew(String uid) {
        return uid;
    }
}
