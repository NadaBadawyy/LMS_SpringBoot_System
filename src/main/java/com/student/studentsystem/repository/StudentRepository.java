package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
