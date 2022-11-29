package com.example.spocktest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class WebController {

    @GetMapping
    public String saturation(){
        return "Hello World!";
    }
}
