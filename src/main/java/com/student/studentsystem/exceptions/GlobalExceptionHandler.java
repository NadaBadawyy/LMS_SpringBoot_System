package com.student.studentsystem.exceptions;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.student.studentsystem.dto.ErrorResponseDTO;
import com.student.studentsystem.dto.ValidationErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleNotFoundStudent(NotFoundException ex,HttpServletRequest request){
        return new ErrorResponseDTO(LocalDateTime.now(), 404, "Not Found", ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public List<ValidationErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getFieldErrors().stream()
                .map((error)-> new ValidationErrorResponseDTO(error.getField(), error.getDefaultMessage())
                ).toList();
    }

    
}