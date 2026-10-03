package com.student.studentsystem.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.student.studentsystem.dto.DepartmentRequestDTO;
import com.student.studentsystem.dto.DepartmentResponseDTO;
import com.student.studentsystem.dto.InstructorResponseDTO;
import com.student.studentsystem.dto.StudentResponseDTO;
import com.student.studentsystem.service.DepartmentService;
import com.student.studentsystem.service.InstructorService;
import com.student.studentsystem.service.StudentService;


@RestController
@RequestMapping ("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final InstructorService instructorService;
    public DepartmentController(DepartmentService departmentService, StudentService studentService, InstructorService instructorService){
        this.departmentService=departmentService;
        this.studentService=studentService;
        this.instructorService=instructorService;
    }   
  
    @GetMapping 
    public List<DepartmentResponseDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
    }
    @GetMapping ("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }   
    @GetMapping ("/{departmentId}/students")
    public List<StudentResponseDTO> getStudentsByDepartmentId(@PathVariable Long departmentId)
    {
        return studentService.getStudentsByDepartmentId(departmentId);
    }
    @GetMapping ("/{departmentId}/instructors")
    public List<InstructorResponseDTO> getInstructorsByDepartmentId(@PathVariable Long departmentId){
        return instructorService.getInstructorsByDepartmentId(departmentId);
    }
    @PostMapping
    public DepartmentResponseDTO CreateDepartment(@RequestBody DepartmentRequestDTO department){
        return departmentService.createDepartment(department);
    }
    @PutMapping ("/{id}") 
    public DepartmentResponseDTO updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDTO department){
        return departmentService.updateDepartment(id, department);
    }
    @DeleteMapping ("/{id}")

    public String deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }

}
