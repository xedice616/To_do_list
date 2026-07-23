package com.qwasar.todolist.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class RegisterResponseDto {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

}
