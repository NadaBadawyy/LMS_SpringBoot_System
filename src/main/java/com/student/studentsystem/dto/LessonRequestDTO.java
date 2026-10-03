package com.student.studentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record LessonRequestDTO(
        @NotBlank String title,
        String description,
        @Positive Integer lessonOrder,
        String contentUrl,Long courseId) {}