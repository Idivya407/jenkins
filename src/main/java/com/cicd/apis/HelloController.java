package com.cicd.apis;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
    	System.out.println("run");
    	return "Hello CI/CD!";
    }
}
