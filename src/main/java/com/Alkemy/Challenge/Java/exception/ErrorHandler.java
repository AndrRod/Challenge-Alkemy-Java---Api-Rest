package com.Alkemy.Challenge.Java.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        // toma los errores
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convierte los errores a string
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField()).append(" ").append(f.getDefaultMessage()).append(" "));

        // devuelve el error a formato json
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}