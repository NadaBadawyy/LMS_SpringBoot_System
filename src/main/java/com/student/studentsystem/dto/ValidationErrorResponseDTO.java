package com.student.studentsystem.dto;

public class ValidationErrorResponseDTO {

    private String field;
    private String message;
    public ValidationErrorResponseDTO(String field,String message){
        this.field=field;
        this.message=message;

    }
    public String getField(){
        return field;
    }
    public String getMessage(){
        return message;
    }
}
