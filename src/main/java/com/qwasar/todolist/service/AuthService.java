package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.auth.LoginRequestDto;
import com.qwasar.todolist.dto.auth.LoginResponseDto;
import com.qwasar.todolist.dto.auth.RegisterRequestDto;
import com.qwasar.todolist.dto.auth.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
}
