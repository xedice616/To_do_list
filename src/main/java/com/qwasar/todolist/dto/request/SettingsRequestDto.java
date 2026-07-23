package com.qwasar.todolist.dto.request;

import com.qwasar.todolist.enums.Language;
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
