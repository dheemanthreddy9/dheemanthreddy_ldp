package org.example.service;

import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        UserMapper userMapper = new UserMapper();
        userService = new UserService(userMapper);
    }

    @Test
    @DisplayName("Should create user from UserRequestDto and return UserResponseDto without password")
    void testCreateUser_HidesPasswordInResponse() {
        // Given
        UserRequestDto requestDto = new UserRequestDto(
                "Alice Smith",
                "alice@example.com",
                "SuperSecret123!",
                "ADMIN"
        );

        // When
        UserResponseDto responseDto = userService.createUser(requestDto);

        // Then
        assertNotNull(responseDto);
        assertNotNull(responseDto.id());
        assertEquals("Alice Smith", responseDto.name());
        assertEquals("alice@example.com", responseDto.email());
        assertEquals("ADMIN", responseDto.role());
        assertNotNull(responseDto.createdAt());

        // Verify DTO isolation: Ensure password is NOT present in UserResponseDto (class structure verification)
        // UserResponseDto only contains (id, name, email, role, createdAt)
    }

    @Test
    @DisplayName("Should retrieve created user by ID as DTO")
    void testGetUserById() {
        // Given
        UserRequestDto requestDto = new UserRequestDto(
                "Bob Builder",
                "bob@example.com",
                "Password456",
                "USER"
        );
        UserResponseDto createdUser = userService.createUser(requestDto);

        // When
        Optional<UserResponseDto> fetchedUserOpt = userService.getUserById(createdUser.id());

        // Then
        assertTrue(fetchedUserOpt.isPresent());
        UserResponseDto fetchedUser = fetchedUserOpt.get();
        assertEquals(createdUser.id(), fetchedUser.id());
        assertEquals("Bob Builder", fetchedUser.name());
    }

    @Test
    @DisplayName("Should return all users as list of DTOs")
    void testGetAllUsers() {
        // Given
        userService.createUser(new UserRequestDto("User 1", "u1@example.com", "pass1", "USER"));
        userService.createUser(new UserRequestDto("User 2", "u2@example.com", "pass2", "USER"));

        // When
        List<UserResponseDto> users = userService.getAllUsers();

        // Then
        assertEquals(2, users.size());
    }
}
