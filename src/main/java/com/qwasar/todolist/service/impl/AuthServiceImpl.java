package com.qwasar.todolist.service.impl;

import com.qwasar.todolist.dto.auth.LoginRequestDto;
import com.qwasar.todolist.dto.auth.LoginResponseDto;
import com.qwasar.todolist.dto.auth.RegisterRequestDto;
import com.qwasar.todolist.dto.auth.RegisterResponseDto;
import com.qwasar.todolist.entity.User;
import com.qwasar.todolist.repository.UserRepository;
import com.qwasar.todolist.service.AuthService;
import com.qwasar.todolist.service.SettingsService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SettingsService settingsService;

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (userRepository.existByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        settingsService.createDefaultSettings(user);

        User savedUser = userRepository.save(user);

        return new RegisterResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
                );
    }

    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Invalid username or password")
        );

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return new LoginResponseDto(
                user.getId(),
                user.getUsername()
        );
    }
}
