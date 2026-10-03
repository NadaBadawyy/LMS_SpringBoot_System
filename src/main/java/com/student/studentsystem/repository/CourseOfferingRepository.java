package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.CourseOffering;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {}