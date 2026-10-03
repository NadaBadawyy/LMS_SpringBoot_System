package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {}