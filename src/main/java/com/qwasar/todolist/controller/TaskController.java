package com.qwasar.todolist.controller;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import com.qwasar.todolist.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponseDto createTask(@Valid @RequestBody TaskRequestDto request) {
        return taskService.createTask(request);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id,
                                      @Valid @RequestBody TaskRequestDto request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    public Page<TaskResponseDto> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

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