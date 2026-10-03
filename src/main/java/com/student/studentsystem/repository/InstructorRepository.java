package com.student.studentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor>getInstructorsByDepartmentId(Long id);
    
}
