package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}