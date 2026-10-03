package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Semester;

public interface SemesterRepository extends JpaRepository<Semester, Long> {}