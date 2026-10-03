package com.student.studentsystem.dto;

public record CourseResponseDTO(Long id, String code, String title, String description, Integer credits, Boolean active) {}