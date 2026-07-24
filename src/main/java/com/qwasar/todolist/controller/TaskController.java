package com.qwasar.todolist.controller;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.DashboardResponseDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import com.qwasar.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Tag(
        name = "Task Management",
        description = "CRUD operations for Task Management System"
)
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            Principal principal,
            @Valid @RequestBody TaskRequestDto request
    ) {
        TaskResponseDto createdTask = taskService.createTask(
                principal.getName(),
                request
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public TaskResponseDto updateTask(
            Principal principal,
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request
    ) {
        return taskService.updateTask(
                principal.getName(),
                id,
                request
        );
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            Principal principal,
            @PathVariable Long id
    ) {
        taskService.deleteTask(
                principal.getName(),
                id
        );

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get task by id")
    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(
            Principal principal,
            @PathVariable Long id
    ) {
        return taskService.getTaskById(
                principal.getName(),
                id
        );
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public Page<TaskResponseDto> getAllTasks(
            Principal principal,
            Pageable pageable
    ) {
        return taskService.getAllTasks(
                principal.getName(),
                pageable
        );
    }

    @Operation(summary = "Search tasks by title")
    @GetMapping("/search/title")
    public Page<TaskResponseDto> searchByTitle(
            Principal principal,
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.searchByTitle(
                principal.getName(),
                title,
                pageable
        );
    }

    @Operation(summary = "Search tasks by description")
    @GetMapping("/search/description")
    public Page<TaskResponseDto> searchByDescription(
            Principal principal,
            @RequestParam String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.searchByDescription(
                principal.getName(),
                description,
                pageable
        );
    }

    @Operation(summary = "Filter tasks by status")
    @GetMapping("/status")
    public Page<TaskResponseDto> getTasksByStatus(
            Principal principal,
            @RequestParam Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByStatus(
                principal.getName(),
                status,
                pageable
        );
    }

    @Operation(summary = "Filter tasks by priority")
    @GetMapping("/priority")
    public Page<TaskResponseDto> getTasksByPriority(
            Principal principal,
            @RequestParam Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByPriority(
                principal.getName(),
                priority,
                pageable
        );
    }

    @Operation(summary = "Filter tasks by due date")
    @GetMapping("/due-date")
    public Page<TaskResponseDto> getTasksByDueDate(
            Principal principal,
            @RequestParam LocalDate dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByDueDate(
                principal.getName(),
                dueDate,
                pageable
        );
    }

    @Operation(summary = "Get favorite tasks")
    @GetMapping("/favorites")
    public Page<TaskResponseDto> getFavoriteTasks(
            Principal principal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.getFavoriteTasks(
                principal.getName(),
                pageable
        );
    }

    @Operation(summary = "Get archived tasks")
    @GetMapping("/archived")
    public Page<TaskResponseDto> getArchivedTasks(
            Principal principal,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return taskService.getArchivedTasks(
                principal.getName(),
                pageable
        );
    }

    @Operation(summary = "Get dashboard statistics")
    @GetMapping("/dashboard")
    public DashboardResponseDto getDashboardStatistics(
            Principal principal
    ) {
        return taskService.getDashboardStatistics(
                principal.getName()
        );
    }

    @Operation(summary = "Toggle task favorite status")
    @PatchMapping("/{id}/favorite")
    public TaskResponseDto toggleFavorite(
            Principal principal,
            @PathVariable Long id
    ) {
        return taskService.toggleFavorite(
                principal.getName(),
                id
        );
    }

    @Operation(summary = "Archive task")
    @PatchMapping("/{id}/archive")
    public TaskResponseDto archiveTask(
            Principal principal,
            @PathVariable Long id
    ) {
        return taskService.archiveTask(
                principal.getName(),
                id
        );
    }
}