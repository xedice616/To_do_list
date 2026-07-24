package com.qwasar.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {

    private Long id;
    private String username;
    private String email;
}
