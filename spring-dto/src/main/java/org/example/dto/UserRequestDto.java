package org.example.dto;

public record UserRequestDto(
        String name,
        String email,
        String password,
        String role
) {}
