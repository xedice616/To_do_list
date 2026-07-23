package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.user.ProfileRequestDto;
import com.qwasar.todolist.dto.user.ProfileResponseDto;

public interface ProfileService {

    public ProfileResponseDto updateProfile(
            String currentUsername, ProfileRequestDto request);
}
