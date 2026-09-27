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
import com.student.studentsystem.service.DepartmentService;


@RestController
@RequestMapping ("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    public  DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @GetMapping 
    public List<DepartmentResponseDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
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
