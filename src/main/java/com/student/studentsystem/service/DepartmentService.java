package com.student.studentsystem.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.DepartmentRequestDTO;
import com.student.studentsystem.dto.DepartmentResponseDTO;
import com.student.studentsystem.entity.Department;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.DepartmentRepository;
@Service 
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public  DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }
    public DepartmentResponseDTO mapToDepartmentResponseDTO(Department department){
        DepartmentResponseDTO dept= new DepartmentResponseDTO();
        dept.setId(department.getId());
        dept.setName(department.getName());
        return dept;
    }

    public List<DepartmentResponseDTO> getAllDepartments(){
        return departmentRepository.findAll()
        .stream()
        .map((d)->new DepartmentResponseDTO(d.getId(), d.getName())).toList();

    }
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO department){
        Department dept= new Department();
        dept.setName(department.getName());
        return mapToDepartmentResponseDTO(departmentRepository.save(dept));
    }
    public DepartmentResponseDTO updateDepartment(Long id,DepartmentRequestDTO department){
        Department existingDepartment=departmentRepository.findById(id).orElseThrow(()->new NotFoundException("department not found with id: "+id));
        existingDepartment.setName(department.getName());
        return mapToDepartmentResponseDTO(departmentRepository.save(existingDepartment));
    }
    public String deleteDepartment(Long id){
        Department dept= departmentRepository.findById(id).orElseThrow(()->new NotFoundException("department not found"));
        departmentRepository.delete(dept);
        return "Department has deleted successfully";
    }

    
}
