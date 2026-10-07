package org.polytech.filmapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<String> handle(FilmNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(ActeurNotFoundException.class)
    public ResponseEntity<String> handle(ActeurNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
