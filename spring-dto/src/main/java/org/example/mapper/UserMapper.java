package org.example.mapper;

import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setRole(dto.role() != null ? dto.role() : "USER");
        user.setInternalNotes("Account registered via API");
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
