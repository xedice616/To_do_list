package com.qwasar.todolist.service.impl;

import com.qwasar.todolist.dto.request.SettingsRequestDto;
import com.qwasar.todolist.dto.response.SettingsResponseDto;
import com.qwasar.todolist.entity.User;
import com.qwasar.todolist.entity.UserSettings;
import com.qwasar.todolist.repository.UserRepository;
import com.qwasar.todolist.repository.UserSettingsRepository;
import com.qwasar.todolist.service.SettingsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private final UserSettingsRepository userSettingsRepository;
    private final UserRepository userRepository;

    @Override
    public SettingsResponseDto getSettings(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "User not found: " + username
                        )
                );

        UserSettings userSettings = userSettingsRepository.findByUserId(user.getId()).orElseThrow(
                        () -> new IllegalArgumentException("Settings not found for user: " + username)
                        );

        return new SettingsResponseDto(
                userSettings.getId(),
                userSettings.isDarkMode(),
                userSettings.getLanguage()
        );
    }

    @Override
    public SettingsResponseDto updateSettings(String username, SettingsRequestDto request) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "User not found: " + username
                        )
                );

        UserSettings userSettings = userSettingsRepository.findByUserId(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("Settings not found for user" + username)
        );

        userSettings.setDarkMode(request.isDarkMode());
        userSettings.setLanguage(request.getLanguage());

        UserSettings updatedSettings = userSettingsRepository.save(userSettings);

        return new SettingsResponseDto(
                updatedSettings.getId(),
                updatedSettings.isDarkMode(),
                updatedSettings.getLanguage()
        );
    }

    @Override
    public void createDefaultSettings(User user) {
        UserSettings userSettings = new UserSettings();

        userSettings.setUser(user);

        userSettingsRepository.save(userSettings);

    }
}
