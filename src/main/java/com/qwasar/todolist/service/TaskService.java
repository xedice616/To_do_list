package com.qwasar.todolist.service;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;

import java.time.LocalDate;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto request);

    TaskResponseDto updateTask(Long id, TaskRequestDto request);

    void deleteTask(Long id);

    TaskResponseDto getTaskById(Long id);

    Page<TaskResponseDto> getAllTasks(Pageable pageable);

    Page<TaskResponseDto> searchByTitle(String title, Pageable pageable);

    Page<TaskResponseDto> searchByDescription(String description, Pageable pageable);

    Page<TaskResponseDto> getTasksByStatus(Status status, Pageable pageable);

    Page<TaskResponseDto> getTasksByPriority(Priority priority, Pageable pageable);

    Page<TaskResponseDto> getTasksByDueDate(LocalDate dueDate, Pageable pageable);

    Page<TaskResponseDto> getFavoriteTasks(Pageable pageable);

    Page<TaskResponseDto> getArchivedTasks(Pageable pageable);
}


//Burada dayanırıq. ❌
//
//Hələ TaskServiceImpl yazmırıq.
//
//Çünki əvvəl Exception Handling-i dizayn etmək istəyirəm.
//
//Real layihələrdə belə edilir.
//
//Əgər biz əvvəlcə TaskServiceImpl yazsaq, sonra exception-ları dəyişməli olacağıq.
//
//Mən istəyirəm ki, əvvəldən düzgün quraq.