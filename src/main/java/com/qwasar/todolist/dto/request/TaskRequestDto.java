package com.qwasar.todolist.dto.request;

import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.RepeatType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDto {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    @NotNull(message = "Due time is required")
    private LocalTime dueTime;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @Builder.Default
    private RepeatType repeatType = RepeatType.NEVER;
}