package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}