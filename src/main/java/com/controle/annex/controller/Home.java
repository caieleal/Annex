package com.controle.annex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class Home {

    @GetMapping("home")
    public String helloWorld(){
        return "helloWorldTestDevTools";
    }
}
