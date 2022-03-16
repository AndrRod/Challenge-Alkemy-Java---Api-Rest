package com.Alkemy.Challenge.Java.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
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
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.NOT_FOUND.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorInfo> handleBadReqException(BadRequestException ex, WebRequest request) {
        String message = ex.getMessage();

        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    //    modificando aca abajo las excepciones que vienen por defecto
    @ExceptionHandler(NumberFormatException.class)
    public final ResponseEntity<ErrorInfo> numberFormatException(WebRequest request, NumberFormatException ex) {
        String message = "Error: " + ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorInfo> ilegalArgumentException(WebRequest request, IllegalArgumentException ex) {
        String message = "Error: " + ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorInfo> HttpMessageNotReadableException(WebRequest request, HttpMessageNotReadableException ex) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorInfo> NotSupportedException(WebRequest request, HttpRequestMethodNotSupportedException ex) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.METHOD_NOT_ALLOWED.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorInfo> NotSupportedException(WebRequest request, MissingPathVariableException ex) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorInfo> NotSupportedException(WebRequest request, ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().iterator().next().getMessageTemplate();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PropertyReferenceException.class)
    public final ResponseEntity<ErrorInfo> ExcPropertyReferency(WebRequest request, PropertyReferenceException ex) {
        String message = ex.getMessage();
        ErrorInfo exceptionResponse = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), message,
                ((ServletWebRequest)request).getRequest().getRequestURI());
        return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}