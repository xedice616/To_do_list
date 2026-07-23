package com.qwasar.todolist.repository;

import com.qwasar.todolist.entity.User;
import com.qwasar.todolist.entity.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    Optional<UserSettings> findByUserId(Long userId);

}
