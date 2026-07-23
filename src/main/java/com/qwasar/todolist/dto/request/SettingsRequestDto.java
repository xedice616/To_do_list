package com.qwasar.todolist.dto.settings;

import com.qwasar.todolist.entity.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SettingsRequestDto {

    private boolean darkMode;
    private Language language;

}
