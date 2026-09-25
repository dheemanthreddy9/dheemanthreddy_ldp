package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n=======================================================");
        System.out.println("   SPRING BOOT ADVANCED HIBERNATE MAPPINGS REST API   ");
        System.out.println("   Server ready at: http://localhost:8080             ");
        System.out.println("=======================================================\n");
    }
}
