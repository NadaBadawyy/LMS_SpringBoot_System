package com.student.studentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByDepartmentId(Long departmentId);
    List<Course> findByInstructorId(Long instructorId);
}