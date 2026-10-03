package com.student.studentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AssignmentResponseDTO(Long id, String title, String description, LocalDate dueDate, BigDecimal maxScore) {}