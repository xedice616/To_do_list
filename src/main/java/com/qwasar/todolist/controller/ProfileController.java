package com.qwasar.todolist.controller;


import com.qwasar.todolist.dto.user.ProfileRequestDto;
import com.qwasar.todolist.dto.user.ProfileResponseDto;
import com.qwasar.todolist.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/api/users")
@ResponseBody
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/profile")
    public ProfileResponseDto updateProfile(
            Principal principal,
            @Valid @RequestBody ProfileRequestDto request
    ) {
        return profileService.updateProfile(principal.getName(), request);
    }
}
