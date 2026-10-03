package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.PaymentRequestDTO;
import com.student.studentsystem.dto.PaymentResponseDTO;
import com.student.studentsystem.entity.Payment;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.PaymentRepository;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private PaymentResponseDTO toResponse(Payment payment) {
        return new PaymentResponseDTO(payment.getId(), payment.getAmount(), payment.getPaymentDate(), payment.getPaymentMethod(), payment.getStatus(), payment.getTransactionReference());
    }

    private Payment toEntity(PaymentRequestDTO request) {
        Payment payment = new Payment();
        payment.setAmount(request.amount());
        payment.setPaymentDate(request.paymentDate());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setStatus(request.status());
        payment.setTransactionReference(request.transactionReference());
        return payment;
    }

    public List<PaymentResponseDTO> getAll() {
        return paymentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PaymentResponseDTO getById(Long id) {
        return paymentRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));
    }

    public PaymentResponseDTO create(PaymentRequestDTO request) {
        return toResponse(paymentRepository.save(toEntity(request)));
    }

    public PaymentResponseDTO update(Long id, PaymentRequestDTO request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));
        payment.setAmount(request.amount());
        payment.setPaymentDate(request.paymentDate());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setStatus(request.status());
        payment.setTransactionReference(request.transactionReference());
        return toResponse(paymentRepository.save(payment));
    }

    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found with id: " + id));
        paymentRepository.delete(payment);
    }
}