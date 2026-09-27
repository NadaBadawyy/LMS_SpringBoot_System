package com.student.studentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentResponseDTO {
    @NotNull 
    private Long id;
   @NotBlank 
    private String name;
    @Email
    @NotBlank
    private String email;
    @Max(30) 
    @Min(5)
    private Integer age;
    @NotBlank 
    private String departmentName;
    @NotNull 
    private Long departmentId;

    public StudentResponseDTO(){
        
    }
     public StudentResponseDTO(
            Long id,
            String name,
            String email,
            Integer age,
            Long departmentId,
            String departmentName) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.departmentId= departmentId;
        this.departmentName=departmentName;
    }
       public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
