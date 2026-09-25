package org.example.controller;

import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("POST /api/users should accept Request DTO and return Response DTO JSON")
    void testCreateUserEndpoint() throws Exception {
        UserResponseDto mockResponse = new UserResponseDto(
                1L,
                "Charlie Brown",
                "charlie@example.com",
                "USER",
                LocalDateTime.now()
        );

        Mockito.when(userService.createUser(any(UserRequestDto.class))).thenReturn(mockResponse);

        String jsonPayload = """
                {
                    "name": "Charlie Brown",
                    "email": "charlie@example.com",
                    "password": "SecretPassword123",
                    "role": "USER"
                }
                """;

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Charlie Brown"))
                .andExpect(jsonPath("$.email").value("charlie@example.com"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.password").doesNotExist()); // Ensure password field does NOT exist in JSON!
    }

    @Test
    @DisplayName("GET /api/users should return list of Response DTOs")
    void testGetAllUsersEndpoint() throws Exception {
        UserResponseDto user1 = new UserResponseDto(1L, "User 1", "u1@test.com", "USER", LocalDateTime.now());
        UserResponseDto user2 = new UserResponseDto(2L, "User 2", "u2@test.com", "ADMIN", LocalDateTime.now());

        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("User 1"))
                .andExpect(jsonPath("$[1].role").value("ADMIN"));
    }
}
