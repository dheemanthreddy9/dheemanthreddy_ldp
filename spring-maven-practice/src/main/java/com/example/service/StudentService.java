package com.example.service;

import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void registerStudent() {
        System.out.println("Registering student...");
        studentRepository.saveStudent();
    }
}