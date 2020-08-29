package com.learn.restfulwebservices.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(path = "/")
    public String home(){
        return "Welcome to Acme";
    }
}
