package com.student.studentsystem.dto;

import java.time.LocalDate;

public record CourseOfferingResponseDTO(Long id, String sectionCode, Integer capacity, String status, LocalDate startDate, LocalDate endDate) {}