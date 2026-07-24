package com.qwasar.todolist.service.impl;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.DashboardResponseDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.entity.Task;
import com.qwasar.todolist.entity.User;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import com.qwasar.todolist.exception.ResourceNotFoundException;
import com.qwasar.todolist.mapper.TaskMapper;
import com.qwasar.todolist.repository.TaskRepository;
import com.qwasar.todolist.repository.UserRepository;
import com.qwasar.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDto createTask(
            String username,
            TaskRequestDto request
    ) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + username
                        )
                );

        Task task = taskMapper.toEntity(request);

        task.setStatus(Status.TODO);
        task.setFavorite(false);
        task.setArchived(false);
        task.setDeleted(false);
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }

    @Override
    public TaskResponseDto updateTask(
            String username,
            Long id,
            TaskRequestDto request
    ) {
        Task task = findOwnedTask(username, id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setDueTime(request.getDueTime());
        task.setPriority(request.getPriority());
        task.setRepeatType(request.getRepeatType());

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponse(updatedTask);
    }

    @Override
    public void deleteTask(
            String username,
            Long id
    ) {
        Task task = findOwnedTask(username, id);

        task.setDeleted(true);

        taskRepository.save(task);
    }

    @Override
    public TaskResponseDto getTaskById(
            String username,
            Long id
    ) {
        Task task = findOwnedTask(username, id);

        return taskMapper.toResponse(task);
    }

    @Override
    public Page<TaskResponseDto> getAllTasks(
            String username,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalse(
                        username,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> searchByTitle(
            String username,
            String title,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndTitleContainingIgnoreCase(
                        username,
                        title,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> searchByDescription(
            String username,
            String description,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndDescriptionContainingIgnoreCase(
                        username,
                        description,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> getTasksByStatus(
            String username,
            Status status,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndStatus(
                        username,
                        status,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> getTasksByPriority(
            String username,
            Priority priority,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndPriority(
                        username,
                        priority,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> getTasksByDueDate(
            String username,
            LocalDate dueDate,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndDueDate(
                        username,
                        dueDate,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> getFavoriteTasks(
            String username,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndFavoriteTrue(
                        username,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public Page<TaskResponseDto> getArchivedTasks(
            String username,
            Pageable pageable
    ) {
        return taskRepository
                .findByUser_UsernameAndDeletedFalseAndArchivedTrue(
                        username,
                        pageable
                )
                .map(taskMapper::toResponse);
    }

    @Override
    public DashboardResponseDto getDashboardStatistics(
            String username
    ) {
        return DashboardResponseDto.builder()
                .totalTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalse(
                                        username
                                )
                )
                .completedTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndStatus(
                                        username,
                                        Status.DONE
                                )
                )
                .pendingTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndStatus(
                                        username,
                                        Status.TODO
                                )
                )
                .inProgressTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndStatus(
                                        username,
                                        Status.IN_PROGRESS
                                )
                )
                .cancelledTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndStatus(
                                        username,
                                        Status.CANCELLED
                                )
                )
                .favoriteTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndFavoriteTrue(
                                        username
                                )
                )
                .archivedTasks(
                        taskRepository
                                .countByUser_UsernameAndDeletedFalseAndArchivedTrue(
                                        username
                                )
                )
                .build();
    }

    @Override
    public TaskResponseDto toggleFavorite(
            String username,
            Long id
    ) {
        Task task = findOwnedTask(username, id);

        task.setFavorite(
                !Boolean.TRUE.equals(task.getFavorite())
        );

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponse(updatedTask);
    }

    @Override
    public TaskResponseDto archiveTask(
            String username,
            Long id
    ) {
        Task task = findOwnedTask(username, id);

        task.setArchived(true);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toResponse(updatedTask);
    }

    private Task findOwnedTask(
            String username,
            Long taskId
    ) {
        return taskRepository
                .findByIdAndUser_UsernameAndDeletedFalse(
                        taskId,
                        username
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task not found with id: " + taskId
                        )
                );
    }
}
    //1️⃣ User tapılır
    //User user = userRepository.findById(1L)
    //
    //Hələ login sistemi hazır olmadığı üçün müvəqqəti olaraq id = 1 olan istifadəçini götürürük.
    //
    //2️⃣ DTO → Entity
    //Task task = taskMapper.toEntity(request);
    //
    //Frontend-dən gələn məlumat Task obyektinə çevrilir.
    //
    //3️⃣ Default dəyərlər verilir
    //task.setStatus(Status.TODO);
    //task.setFavorite(false);
    //task.setArchived(false);
    //task.setDeleted(false);
    //
    //İstifadəçi bunları göndərmir.
    //
    //Backend özü təyin edir.
    //
    //Bu, daha təhlükəsizdir.
    //
    //4️⃣ User əlaqələndirilir
    //task.setUser(user);
    //
    //Task hansı istifadəçiyə məxsusdursa, onu qeyd edirik.
    //
    //5️⃣ Database-ə yazılır
    //Task savedTask = taskRepository.save(task);
    //
    //Hibernate bunu INSERT sorğusuna çevirəcək.
    //
    //6️⃣ Entity → ResponseDTO
    //return taskMapper.toResponse(savedTask);
    //
    //İstifadəçiyə TaskResponseDto qaytarılır.