package com.student.studentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentResponseDTO(Long id, BigDecimal amount, LocalDate paymentDate, String paymentMethod, String status, String transactionReference) {}