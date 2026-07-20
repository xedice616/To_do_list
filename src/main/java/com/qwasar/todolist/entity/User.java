package com.qwasar.todolist.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // Tells the SpringDATA JPA that this is an object that kept in database
@Table(name="users") // The table name that will be created in database
public class User {

    @Id //this field is a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // postgresql will create the id itself
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 50
    )
    private String username;

    @Column(
            nullable = false,
            unique = true,
            length = 100
        )
    private String email;

    @Column(
            nullable = false,
            length = 255
    )
    private String password;

    @CreationTimestamp
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(
            nullable = false
    )
    private LocalDateTime updatedAt;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
