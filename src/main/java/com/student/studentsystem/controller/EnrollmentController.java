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

import com.student.studentsystem.dto.EnrollmentRequestDTO;
import com.student.studentsystem.dto.EnrollmentResponseDTO;
import com.student.studentsystem.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) { this.enrollmentService = enrollmentService; }

    @GetMapping
    public List<EnrollmentResponseDTO> getAll() { return enrollmentService.getAll(); }

    @GetMapping("/{id}")
    public EnrollmentResponseDTO getById(@PathVariable Long id) { return enrollmentService.getById(id); }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> create(@Valid @RequestBody EnrollmentRequestDTO request) {
        EnrollmentResponseDTO created = enrollmentService.create(request);
        return ResponseEntity.created(URI.create("/enrollments/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public EnrollmentResponseDTO update(@PathVariable Long id, @Valid @RequestBody EnrollmentRequestDTO request) {
        return enrollmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}