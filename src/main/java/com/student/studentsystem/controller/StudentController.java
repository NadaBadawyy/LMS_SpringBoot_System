package com.student.studentsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentsystem.dto.PageResponseDTO;
import com.student.studentsystem.dto.StudentRequestDTO;
import com.student.studentsystem.dto.StudentResponseDTO;
import com.student.studentsystem.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/students")
public class StudentController {
    public final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @GetMapping 
    public PageResponseDTO<StudentResponseDTO> getAllStudents(@RequestParam(required = false) String name,@RequestParam(required = false) Long departmentId,Pageable pageable){
        return studentService.getAllStudents(name,departmentId,pageable);
        
    }
    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping 
    public StudentResponseDTO createStudent(@Valid @RequestBody StudentRequestDTO student){
        return studentService.createStudent(student);
    }
    @DeleteMapping ("/{id}")
    public String deleteStudentById(@PathVariable Long id){
         return studentService.deleteStudent(id);
    }
    @PutMapping ("/{id}")
    public StudentResponseDTO updateStudentById(@PathVariable Long id,@Valid @RequestBody StudentRequestDTO student){
        return studentService.updateStudentById(id, student);
    }

    




}
