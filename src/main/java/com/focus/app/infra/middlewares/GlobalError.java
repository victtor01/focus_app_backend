package com.focus.app.infra.middlewares;

import com.focus.app.infra.config.errors.BadRequestException;
import com.focus.app.infra.config.errors.ErrorResponse;
import com.focus.app.infra.config.errors.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalError {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(BadRequestException ex) {
        ErrorResponse error = new ErrorResponse(ex.getType(), ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFound(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getType(), ex.getMessage(), ex.getStatusCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
