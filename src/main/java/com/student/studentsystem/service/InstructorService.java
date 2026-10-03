package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.InstructorRequestDTO;
import com.student.studentsystem.dto.InstructorResponseDTO;
import com.student.studentsystem.entity.Department;
import com.student.studentsystem.entity.Instructor;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.DepartmentRepository;
import com.student.studentsystem.repository.InstrunctorRepository;

@Service 
public class InstructorService {
    private final InstrunctorRepository instrunctorRepository;
    private final DepartmentRepository departmentRepository;
    public  InstructorService(InstrunctorRepository instrunctorRepository, DepartmentRepository departmentRepository){
        this.instrunctorRepository=instrunctorRepository;
        this.departmentRepository=departmentRepository;
    }
    public InstructorResponseDTO mapToInstructorResponseDTO(Instructor instructor){
        return new InstructorResponseDTO(instructor.getId(), instructor.getName(), instructor.getEmail(), instructor.getDepartment().getId(), instructor.getDepartment().getName());
    }

    public List<InstructorResponseDTO> getAllInstructors() {
        return instrunctorRepository.findAll().stream()
        .map(this::mapToInstructorResponseDTO)
        .toList();

    }
    public InstructorResponseDTO getInstructorById(Long id) {
        return instrunctorRepository.findById(id)
        .map(this::mapToInstructorResponseDTO)
        .orElseThrow(()->new NotFoundException("Instructor not found with id: "+id));
    }
    public InstructorResponseDTO createInstructor(InstructorRequestDTO instructordto) {
        Department department = departmentRepository.findById(instructordto.getDepartmentId())
            .orElseThrow(() -> new NotFoundException("Department not found with id: " + instructordto.getDepartmentId()));
        Instructor instructor= new Instructor();
        instructor.setName(instructordto.getName());
        instructor.setEmail(instructordto.getEmail());
        instructor.setDepartment(department);
        return mapToInstructorResponseDTO(instrunctorRepository.save(instructor));

    }
    public InstructorResponseDTO updateInstructor(Long id, InstructorRequestDTO instructordto) {
        Instructor existingInstructor=instrunctorRepository.findById(id)
        .orElseThrow(()->new NotFoundException("Instructor not found with id: "+id));
        existingInstructor.setName(instructordto.getName());
        existingInstructor.setEmail(instructordto.getEmail());
        return mapToInstructorResponseDTO(instrunctorRepository.save(existingInstructor));
    }
    public String deleteInstructor(Long id) {
        Instructor instructor=instrunctorRepository.findById(id)
        .orElseThrow(()->new NotFoundException("Instructor not found with id: "+
id));
        instrunctorRepository.delete(instructor);
        return "Instructor has been deleted successfully";  }
        
        public List<InstructorResponseDTO> getInstructorsByDepartmentId(Long id){
            return instrunctorRepository.getInstructorsByDepartmentId(id).stream().map(s->mapToInstructorResponseDTO(s)).toList();
        }
    
}
