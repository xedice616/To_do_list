package com.qwasar.todolist.dto.response;

import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.RepeatType;
import com.qwasar.todolist.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {

    private Long id;

    private Long userId;

    private String title;

    private String description;

    private LocalDate dueDate;

    private LocalTime dueTime;

    private Status status;

    private Priority priority;

    private RepeatType repeatType;

    private Boolean favorite;

    private Boolean archived;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}