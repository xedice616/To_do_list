package com.qwasar.todolist.dto.response;

import com.qwasar.todolist.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SettingsResponseDto {

    private Long id;
    private boolean darkMode;
    private Language language;
}
