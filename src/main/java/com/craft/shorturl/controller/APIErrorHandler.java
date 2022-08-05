package com.craft.shorturl.controller;

import com.craft.shorturl.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class APIErrorHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                "Unexpected error occurred while processing", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ IncorrectAlgorithmException.class })
    public ResponseEntity<Object> handleHashAlgoException(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                "Unexpected error occurred while processing", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ EmptyURLException.class })
    public ResponseEntity<Object> handleEmptyOriginalURlException(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                "URL should not be empty", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ErrorWhileSavingToDB.class })
    public ResponseEntity<Object> handleDBSaveError(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ URLNotFound.class })
    public ResponseEntity<Object> handleURLMissInDB(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                "URL Not found in DB", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ UrlCreationNotAllowed.class })
    public ResponseEntity<Object> handleConstrainViolation(
            Exception ex, WebRequest request) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
