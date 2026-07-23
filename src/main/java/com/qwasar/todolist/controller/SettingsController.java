package com.qwasar.todolist.controller;

import com.qwasar.todolist.dto.request.SettingsRequestDto;
import com.qwasar.todolist.dto.response.SettingsResponseDto;
import com.qwasar.todolist.service.SettingsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@ResponseBody
@RequestMapping("/api/settings")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public SettingsResponseDto getSettings(
            Principal principal
    ) {
        return settingsService.getSettings(principal.getName());
    }

    @PutMapping
    public SettingsResponseDto updateSettings(
            Principal principal,
            @Valid @RequestBody SettingsRequestDto request
    ) {
        return settingsService.updateSettings(principal.getName(), request);
    }

}
