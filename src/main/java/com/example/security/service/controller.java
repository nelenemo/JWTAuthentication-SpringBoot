package com.example.security.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/api/api")
public class controller {
@GetMapping("/hi")
    public String hello(){
        return"hello world";
    }
}
