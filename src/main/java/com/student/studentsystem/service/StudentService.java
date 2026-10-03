package com.student.studentsystem.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.PageResponseDTO;
import com.student.studentsystem.dto.StudentRequestDTO;
import com.student.studentsystem.dto.StudentResponseDTO;
import com.student.studentsystem.entity.Department;
import com.student.studentsystem.entity.Student;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.DepartmentRepository;
import com.student.studentsystem.repository.StudentRepository;
import com.student.studentsystem.specification.StudentSpecification;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public StudentResponseDTO maptoStudentDTO(Student student) {
        return new StudentResponseDTO(student.getId(), student.getName(), student.getEmail(), student.getAge(),
                student.getDepartment().getId(), student.getDepartment().getName());
    }

    public PageResponseDTO<StudentResponseDTO> getAllStudents(String name, Long departmentId, Pageable pageable) {
        Page<Student> studentPage;
        Specification<Student>specification = (root,query, builder)->null;

        if(name!=null &&!name.isBlank()){
            specification=specification.and(
                StudentSpecification.hasName(name)
            );
            
        }
        if(departmentId!=null){
           specification= specification.and(
                StudentSpecification.hasDepartment(departmentId)
            );
        }
        studentPage= studentRepository.findAll(specification,pageable);
        // studentPage = studentRepository.searchStudents(name, departmentId, pageable);

        List<StudentResponseDTO> students = studentPage.map(s -> maptoStudentDTO(s)).getContent();
        return new PageResponseDTO<>(students, studentPage.getNumber(), studentPage.getSize(),
                studentPage.getTotalElements(), studentPage.getTotalPages());

    }

    public StudentResponseDTO getStudentById(Long id) {
        return maptoStudentDTO(studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id)));
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentdto) {
        Department department = departmentRepository.findById(studentdto.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("department not found"));
        Student student = new Student();
        student.setName(studentdto.getName());
        student.setAge(studentdto.getAge());
        student.setEmail(studentdto.getEmail());
        student.setDepartment(department);
        return maptoStudentDTO(studentRepository.save(student));
    }

    public String deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));

        studentRepository.delete(student);
        return "Student deleted successfully";

    }

    public StudentResponseDTO updateStudentById(Long id, StudentRequestDTO student) {
        Department department = departmentRepository.findById(student.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("department not found"));
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(department);
        return maptoStudentDTO(studentRepository.save(existingStudent));
    }
    public List<StudentResponseDTO> getStudentsByDepartmentId(Long departmentId) {
        return studentRepository.getStudentsByDepartmentId(departmentId).stream()
                .map(student -> maptoStudentDTO(student))
                .toList();
    }

}
