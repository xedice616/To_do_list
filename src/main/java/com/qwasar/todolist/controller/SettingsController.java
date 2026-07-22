package com.qwasar.todolist.controller;

import com.qwasar.todolist.dto.settings.SettingsRequestDto;
import com.qwasar.todolist.dto.settings.SettingsResponseDto;
import com.qwasar.todolist.service.SettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping("/{userId}")
    public SettingsResponseDto getSettings(@PathVariable Long userId) {
        return settingsService.getSettings(userId);
    }

    @PutMapping("/{userId}")
    public SettingsResponseDto updateSettings(
            @PathVariable Long userId,
            @RequestBody SettingsRequestDto request
    ) {
        return settingsService.updateSettings(userId, request);
    }

}
