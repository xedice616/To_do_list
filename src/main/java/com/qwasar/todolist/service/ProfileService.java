package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.request.ProfileRequestDto;
import com.qwasar.todolist.dto.response.ProfileResponseDto;

public interface ProfileService {

    public ProfileResponseDto updateProfile(
            String currentUsername, ProfileRequestDto request);
}
