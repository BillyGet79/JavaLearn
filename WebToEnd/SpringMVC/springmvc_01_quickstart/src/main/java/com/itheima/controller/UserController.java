package com.itheima.controller;

import org.springframework.stereotype.Controller;

//2.定义controller
//2.1 使用@Controller定义bean
@Controller
public class UserController {

    public String save() {
        System.out.println("user save ...");
        return "{'module':'springmvc'}";
    }
    
}
