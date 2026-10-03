package com.student.studentsystem.controller;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentsystem.dto.PaymentRequestDTO;
import com.student.studentsystem.dto.PaymentResponseDTO;
import com.student.studentsystem.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) { this.paymentService = paymentService; }

    @GetMapping
    public List<PaymentResponseDTO> getAll() { return paymentService.getAll(); }

    @GetMapping("/{id}")
    public PaymentResponseDTO getById(@PathVariable Long id) { return paymentService.getById(id); }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO request) {
        PaymentResponseDTO created = paymentService.create(request);
        return ResponseEntity.created(URI.create("/payments/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public PaymentResponseDTO update(@PathVariable Long id, @Valid @RequestBody PaymentRequestDTO request) {
        return paymentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}