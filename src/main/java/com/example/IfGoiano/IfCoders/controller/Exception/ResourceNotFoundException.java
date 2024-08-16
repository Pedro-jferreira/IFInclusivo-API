package com.example.IfGoiano.IfCoders.controller.Exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Resource not found. ID: " + id);
    }
}