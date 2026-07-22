package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.settings.SettingsResponseDto;
import com.qwasar.todolist.dto.settings.SettingsRequestDto;
import com.qwasar.todolist.entity.User;

public interface SettingsService {

    void createDefaultSettings(User user);
    SettingsResponseDto getSettings(Long userId);
    SettingsResponseDto updateSettings(
            Long userId,
            SettingsRequestDto request
    );
}
