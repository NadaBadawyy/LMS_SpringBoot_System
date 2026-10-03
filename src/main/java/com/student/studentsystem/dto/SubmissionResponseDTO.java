package com.student.studentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubmissionResponseDTO(Long id, LocalDateTime submittedAt, String content, BigDecimal score, String feedback) {}