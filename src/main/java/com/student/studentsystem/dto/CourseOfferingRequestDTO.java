package com.student.studentsystem.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CourseOfferingRequestDTO(
        @NotBlank String sectionCode,
        @Positive Integer capacity,
        @NotBlank String status,
        LocalDate startDate,
        LocalDate endDate
    
    ) {}