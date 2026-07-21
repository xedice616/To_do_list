package com.qwasar.todolist.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}


//Bu exception məsələn belə hallarda istifadə olunacaq:
//
//Task with id 5 not found.