package com.student.studentsystem.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubmissionRequestDTO(
        @NotNull LocalDateTime submittedAt,
        @NotBlank String content,
        BigDecimal score,
        String feedback) {}