package com.student.studentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentResponseDTO(Long id, LocalDate enrollmentDate, String status, BigDecimal grade) {}