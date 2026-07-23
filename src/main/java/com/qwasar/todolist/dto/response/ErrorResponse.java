package com.qwasar.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;
}


//Bu, API-nin bütün xəta cavablarını eyni formatda qaytaracaq.
//
//Məsələn:
//
//{
//  "timestamp": "2026-07-20T13:15:30",
//  "status": 404,
//  "error": "Not Found",
//  "message": "Task with id 10 not found.",
//  "path": "/tasks/10"
//}