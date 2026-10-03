package com.student.studentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.CourseOffering;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    List<CourseOffering> getCourseOfferingsByInstructorId(Long instructorId);
    List<CourseOffering> getCourseOfferingsByCourseId(Long courseId);
    List<CourseOffering> getCourseOfferingsBySemesterId(Long semesterId);
}