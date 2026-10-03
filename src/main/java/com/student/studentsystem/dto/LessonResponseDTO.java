package com.student.studentsystem.dto;

public record LessonResponseDTO(Long id, String title, String description, Integer lessonOrder, String contentUrl, Integer durationMinutes) {}