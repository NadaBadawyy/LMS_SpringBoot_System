package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {}