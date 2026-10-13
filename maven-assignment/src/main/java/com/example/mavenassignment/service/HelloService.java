package com.example.mavenassignment.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {

    public String getGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "World";
        }
        return "Hello, " + name + "! Welcome to Maven Spring Boot Application.";
    }

    public Map<String, String> getAppInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("appName", "maven-assignment");
        info.put("status", "UP");
        info.put("framework", "Spring Boot 3");
        info.put("packagedWith", "Apache Maven");
        return info;
    }
}
