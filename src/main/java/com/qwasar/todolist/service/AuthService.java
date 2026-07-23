package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.request.LoginRequestDto;
import com.qwasar.todolist.dto.response.LoginResponseDto;
import com.qwasar.todolist.dto.request.RegisterRequestDto;
import com.qwasar.todolist.dto.response.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
}
