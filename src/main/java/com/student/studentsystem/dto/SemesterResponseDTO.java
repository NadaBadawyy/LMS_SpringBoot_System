package com.student.studentsystem.dto;

import java.time.LocalDate;

public record SemesterResponseDTO(Long id, String name, LocalDate startDate, LocalDate endDate) {}