package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {}