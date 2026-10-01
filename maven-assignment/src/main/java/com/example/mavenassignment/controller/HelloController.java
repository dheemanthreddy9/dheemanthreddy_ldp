package com.example.mavenassignment.controller;

import com.example.mavenassignment.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String index() {
        return helloService.getGreeting("User");
    }

    @GetMapping("/api/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return helloService.getGreeting(name);
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        return helloService.getAppInfo();
    }
}
