package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.settings.SettingsResponseDto;
import com.qwasar.todolist.dto.settings.SettingsRequestDto;
import com.qwasar.todolist.entity.User;

public interface SettingsService {

    void createDefaultSettings(User user);
    SettingsResponseDto getSettings(String username);
    SettingsResponseDto updateSettings(
            String username,
            SettingsRequestDto request
    );
}
