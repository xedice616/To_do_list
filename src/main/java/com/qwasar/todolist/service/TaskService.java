package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.DashboardResponseDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface TaskService {

    TaskResponseDto createTask(
            String username,
            TaskRequestDto request
    );

    TaskResponseDto updateTask(
            String username,
            Long id,
            TaskRequestDto request
    );

    void deleteTask(
            String username,
            Long id
    );

    TaskResponseDto getTaskById(
            String username,
            Long id
    );

    Page<TaskResponseDto> getAllTasks(
            String username,
            Pageable pageable
    );

    Page<TaskResponseDto> searchByTitle(
            String username,
            String title,
            Pageable pageable
    );

    Page<TaskResponseDto> searchByDescription(
            String username,
            String description,
            Pageable pageable
    );

    Page<TaskResponseDto> getTasksByStatus(
            String username,
            Status status,
            Pageable pageable
    );

    Page<TaskResponseDto> getTasksByPriority(
            String username,
            Priority priority,
            Pageable pageable
    );

    Page<TaskResponseDto> getTasksByDueDate(
            String username,
            LocalDate dueDate,
            Pageable pageable
    );

    Page<TaskResponseDto> getFavoriteTasks(
            String username,
            Pageable pageable
    );

    Page<TaskResponseDto> getArchivedTasks(
            String username,
            Pageable pageable
    );

    DashboardResponseDto getDashboardStatistics(
            String username
    );

    TaskResponseDto toggleFavorite(
            String username,
            Long id
    );

    TaskResponseDto archiveTask(
            String username,
            Long id
    );
}