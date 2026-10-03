package com.student.studentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AssignmentRequestDTO(
        @NotBlank String title,
        String description,
        @NotNull LocalDate dueDate,
        @NotNull @Positive BigDecimal maxScore) {}