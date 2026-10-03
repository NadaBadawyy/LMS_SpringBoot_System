package com.student.studentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CourseRequestDTO(
        @NotBlank String code,
        @NotBlank String title,
        String description,
        @NotNull @Positive Integer credits,
        @NotNull Boolean active) {}