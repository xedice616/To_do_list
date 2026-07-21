package com.qwasar.todolist.controller;


import com.qwasar.todolist.dto.auth.RegisterRequestDto;
import com.qwasar.todolist.dto.auth.RegisterResponseDto;
import com.qwasar.todolist.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponseDto register(
            @Valid
            @RequestBody RegisterRequestDto request
            ) {
        return authService.register(request);
    }


}
