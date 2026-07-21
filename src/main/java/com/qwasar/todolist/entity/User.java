package com.qwasar.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity // Tells the SpringDATA JPA that this is an object that kept in database
@Getter // this is lombok, helps to create getters and setters automaticaly
@Setter
@NoArgsConstructor // this helps us to create no args constructor also.
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

}
