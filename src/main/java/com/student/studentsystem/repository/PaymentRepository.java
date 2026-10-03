package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}