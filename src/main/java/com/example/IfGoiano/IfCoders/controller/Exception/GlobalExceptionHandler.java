package com.example.IfGoiano.IfCoders.controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequest(BadRequestException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Bad request");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleUnauthorized(UnauthorizedException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Unauthorized");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ExceptionResponse> handleInternalServerError(InternalServerErrorException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Internal server error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponse> handleConflict(ConflictException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Conflict");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ExceptionResponse> handleDataBaseException(DataBaseException ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), "Database error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(new Date(), "Something went wrong. Please try again later.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
