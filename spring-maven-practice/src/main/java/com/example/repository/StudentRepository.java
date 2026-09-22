package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public void saveStudent() {
        System.out.println("Student saved successfully");
    }
}