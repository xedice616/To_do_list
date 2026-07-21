package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.auth.RegisterRequestDto;
import com.qwasar.todolist.entity.User;

public interface AuthService {

    User register(RegisterRequestDto request);
}
