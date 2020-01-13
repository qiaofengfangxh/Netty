package com.qiaofengfangxh.netty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String getValue() {
        return "开始我们的netty学习吧！";
    }
}
