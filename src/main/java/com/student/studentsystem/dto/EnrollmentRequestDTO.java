package com.student.studentsystem.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO(
        @NotNull LocalDate enrollmentDate,
        @NotBlank String status,
        BigDecimal grade) {}