package com.example.IfGoiano.IfCoders.service.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object id){
        super("resource not found. id: "+ id);
    }
}
