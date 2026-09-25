package org.example.service;

import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> userDatabase = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User userEntity = userMapper.toEntity(requestDto);
        Long generatedId = idGenerator.getAndIncrement();
        userEntity.setId(generatedId);
        userDatabase.put(generatedId, userEntity);
        return userMapper.toResponseDto(userEntity);
    }


    public Optional<UserResponseDto> getUserById(Long id) {
        User user = userDatabase.get(id);
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(userMapper.toResponseDto(user));
    }


    public List<UserResponseDto> getAllUsers() {
        return userDatabase.values().stream()
                .map(userMapper::toResponseDto)
                .toList();
    }
}
