package com.qwasar.todolist.controller;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.DashboardResponseDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import com.qwasar.todolist.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(
        name = "Task Management",
        description = "CRUD operations for Task Management System"
)
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;



    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public TaskResponseDto updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto request) {

        return taskService.updateTask(id, request);
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get task by id")
    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {

        return taskService.getTaskById(id);
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public Page<TaskResponseDto> getAllTasks(Pageable pageable) {

        return taskService.getAllTasks(pageable);
    }
    @GetMapping("/search/title")
    public Page<TaskResponseDto> searchByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.searchByTitle(title, pageable);
    }

    @GetMapping("/search/description")
    public Page<TaskResponseDto> searchByDescription(
            @RequestParam String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.searchByDescription(description, pageable);
    }

    @GetMapping("/status")
    public Page<TaskResponseDto> getTasksByStatus(
            @RequestParam Status status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByStatus(status, pageable);
    }

    @GetMapping("/priority")
    public Page<TaskResponseDto> getTasksByPriority(
            @RequestParam Priority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByPriority(priority, pageable);
    }

    @GetMapping("/due-date")
    public Page<TaskResponseDto> getTasksByDueDate(
            @RequestParam LocalDate dueDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.getTasksByDueDate(dueDate, pageable);
    }

    @GetMapping("/favorites")
    public Page<TaskResponseDto> getFavoriteTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.getFavoriteTasks(pageable);
    }

    @GetMapping("/archived")
    public Page<TaskResponseDto> getArchivedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return taskService.getArchivedTasks(pageable);
    }



    @GetMapping("/dashboard")
    public DashboardResponseDto getDashboardStatistics() {
        return taskService.getDashboardStatistics();
    }



    @PatchMapping("/{id}/favorite")
    public TaskResponseDto toggleFavorite(@PathVariable Long id) {

        return taskService.toggleFavorite(id);
    }

    @PatchMapping("/{id}/archive")
    public TaskResponseDto archiveTask(@PathVariable Long id) {

        return taskService.archiveTask(id);
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @Valid @RequestBody TaskRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request));
    }
}

//Bu controller nə edir?
//
//Bu controller aşağıdakı endpoint-ləri yaradır:
//
//HTTP Method	Endpoint	Təyinatı
//POST	/api/tasks	Yeni task yaratmaq
//PUT	/api/tasks/{id}	Task yeniləmək
//DELETE	/api/tasks/{id}	Soft delete
//GET	/api/tasks/{id}	Bir task gətirmək
//GET	/api/tasks	Bütün task-lar (pagination ilə)
//GET	/api/tasks/search/title	Title üzrə axtarış
//GET	/api/tasks/search/description	Description üzrə axtarış
//GET	/api/tasks/status	Status filter
//GET	/api/tasks/priority	Priority filter
//GET	/api/tasks/due-date	Due Date filter
//GET	/api/tasks/favorites	Favorite task-lar
//GET	/api/tasks/archived	Archive edilmiş task-lar