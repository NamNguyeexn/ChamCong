package com.ex.initproj.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.ServletException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e) {
        return ResponseEntity.status(500).body("GOT IOEXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleServletException (ServletException e) {
        return ResponseEntity.status(500).body("GOT SERVLET EXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException (EmptyResultDataAccessException e) {
        return ResponseEntity.status(500).body("GOT EmptyResultDataAccessException : " + e.getMessage());
    }
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException (MalformedJwtException e){
        return ResponseEntity.status(500).body("GOT MALFORMED JWT EXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException e){
        return ResponseEntity.status(500).body("GOT EXPIRED JWT EXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<String> handleUnsupportedJwtException(UnsupportedJwtException e){
        return ResponseEntity.status(500).body("GOT UNSUPPORTED JWT EXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.status(500).body("GOT ILLEGAL ARGUMENT EXCEPTION :" + e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException (Exception e) {
        return ResponseEntity.status(500).body("GOT GLOBAL EXCEPTION :" + e.getMessage());
    }
}
