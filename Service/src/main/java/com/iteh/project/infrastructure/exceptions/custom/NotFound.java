package com.iteh.project.infrastructure.exceptions.custom;

public class NotFound extends RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
