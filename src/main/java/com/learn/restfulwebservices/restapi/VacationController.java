package com.learn.restfulwebservices.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationController {

    @GetMapping(path = "/")
    public String vacation() {
        return "Welcome to ACME Vacation portal";
    }

}
