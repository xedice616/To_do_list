package com.qwasar.todolist.controller;


import com.qwasar.todolist.dto.request.LoginRequestDto;
import com.qwasar.todolist.dto.response.LoginResponseDto;
import com.qwasar.todolist.dto.request.RegisterRequestDto;
import com.qwasar.todolist.dto.response.RegisterResponseDto;
import com.qwasar.todolist.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
@ResponseBody
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(
            @Valid
            @RequestBody LoginRequestDto request
    ) {
        return authService.login(request);
    }


}
