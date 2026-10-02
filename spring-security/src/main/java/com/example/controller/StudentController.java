package com.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/students")
    public String studentsEndpoint() {
        return "Student data - authenticated user only";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin data";
    }

    @GetMapping("/user-info")
    public String userInfo(Authentication authentication) {

        return "User ID: " + authentication.getName()
                + "\nRoles: " + authentication.getAuthorities();
    }
}
