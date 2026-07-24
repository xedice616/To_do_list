package com.qwasar.todolist.repository;

import com.qwasar.todolist.entity.Task;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUser_UsernameAndDeletedFalse(
            Long id,
            String username
    );

    Page<Task> findByUser_UsernameAndDeletedFalse(
            String username,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndTitleContainingIgnoreCase(
            String username,
            String title,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndDescriptionContainingIgnoreCase(
            String username,
            String description,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndStatus(
            String username,
            Status status,
            Pageable pageable


    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndPriority(
            String username,
            Priority priority,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndDueDate(
            String username,
            LocalDate dueDate,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndFavoriteTrue(
            String username,
            Pageable pageable
    );

    Page<Task> findByUser_UsernameAndDeletedFalseAndArchivedTrue(
            String username,
            Pageable pageable
    );

    long countByUser_UsernameAndDeletedFalse(
            String username
    );

    long countByUser_UsernameAndDeletedFalseAndStatus(
            String username,
            Status status
    );

    long countByUser_UsernameAndDeletedFalseAndFavoriteTrue(
            String username
    );

    long countByUser_UsernameAndDeletedFalseAndArchivedTrue(
            String username
    );

    List<Task> findByDeletedFalseAndArchivedFalseAndStatusNot(
            Status status
    );
}