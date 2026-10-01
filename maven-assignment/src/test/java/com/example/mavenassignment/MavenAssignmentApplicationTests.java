package com.example.mavenassignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MavenAssignmentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello?name=Developer"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Developer! Welcome to Maven Spring Boot Application."));
    }

    @Test
    void testInfoEndpoint() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk());
    }
}
