package com.qwasar.todolist.service.impl;

import com.qwasar.todolist.dto.user.ProfileRequestDto;
import com.qwasar.todolist.dto.user.ProfileResponseDto;
import com.qwasar.todolist.entity.User;
import com.qwasar.todolist.repository.UserRepository;
import com.qwasar.todolist.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private UserRepository userRepository;

    @Override
    public ProfileResponseDto updateProfile(String currentUsername, ProfileRequestDto request) {
        User user = userRepository.findByUsername(currentUsername).orElseThrow(
                () -> new IllegalArgumentException("User not found: " + currentUsername)
        );

        if (!user.getUsername().equals(request.getUsername()) &&
                userRepository.existByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (!user.getEmail().equals(request.getEmail()) &&
                userRepository.existByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        return new ProfileResponseDto(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getEmail()
        );
    }
}
