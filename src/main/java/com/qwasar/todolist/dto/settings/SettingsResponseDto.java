package com.qwasar.todolist.dto.settings;

import com.qwasar.todolist.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SettingsResponseDto {

    private Long id;
    private boolean darkMode;
    private Language language;
}
