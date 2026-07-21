package com.qwasar.todolist.service.impl;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.entity.Task;
import com.qwasar.todolist.entity.User;
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
import com.qwasar.todolist.enums.Priority;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDto createTask(TaskRequestDto request) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
    public TaskResponseDto updateTask(Long id, TaskRequestDto request) {

        Task task = taskRepository.findById(id)//database den hemin task tapilir
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(request.getTitle()); //Gələn məlumatlarla köhnə task yenilənir.
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setDueTime(request.getDueTime());
        task.setPriority(request.getPriority());
        task.setRepeatType(request.getRepeatType());

        Task updatedTask = taskRepository.save(task); //Hibernate UPDATE sorğusu göndərir.

        return taskMapper.toResponse(updatedTask);//İstifadəçiyə yenilənmiş Task qaytarılır.
    }


    @Override
    public Page<TaskResponseDto> searchByTitle(String title, Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndTitleContainingIgnoreCase(title, pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> searchByDescription(String description, Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndDescriptionContainingIgnoreCase(description, pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> getTasksByStatus(Status status, Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndStatus(status, pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> getTasksByPriority(Priority priority, Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndPriority(priority, pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> getTasksByDueDate(LocalDate dueDate, Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndDueDate(dueDate, pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> getFavoriteTasks(Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndFavoriteTrue(pageable)
                .map(taskMapper::toResponse);
    }


    @Override
    public Page<TaskResponseDto> getArchivedTasks(Pageable pageable) {

        return taskRepository
                .findByDeletedFalseAndArchivedTrue(pageable)
                .map(taskMapper::toResponse);
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));
//burada task silinmir sadece dleted = true edilir. bu soft delete adlanir. database de silinmir sadece gizlenir.

        task.setDeleted(true);

        taskRepository.save(task);
    }


    @Override
    public TaskResponseDto getTaskById(Long id) {

        Task task = taskRepository.findById(id)//hemin id li task database de axtarilir
                .orElseThrow(() -> //eger tapilmasa bu isleyir. yeni 404 not found xetasi
                        new ResourceNotFoundException("Task not found with id: " + id));

        return taskMapper.toResponse(task);
    }

    @Override
    public Page<TaskResponseDto> getAllTasks(Pageable pageable) {
        return taskRepository
                .findByDeletedFalse(pageable)
                .map(taskMapper::toResponse);
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


}