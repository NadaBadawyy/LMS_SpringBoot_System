package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.EnrollmentRequestDTO;
import com.student.studentsystem.dto.EnrollmentResponseDTO;
import com.student.studentsystem.entity.Enrollment;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    private EnrollmentResponseDTO toResponse(Enrollment enrollment) {
        return new EnrollmentResponseDTO(enrollment.getId(), enrollment.getEnrollmentDate(), enrollment.getStatus(), enrollment.getGrade());
    }

    private Enrollment toEntity(EnrollmentRequestDTO request) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentDate(request.enrollmentDate());
        enrollment.setStatus(request.status());
        enrollment.setGrade(request.grade());
        return enrollment;
    }

    public List<EnrollmentResponseDTO> getAll() {
        return enrollmentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public EnrollmentResponseDTO getById(Long id) {
        return enrollmentRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Enrollment not found with id: " + id));
    }

    public EnrollmentResponseDTO create(EnrollmentRequestDTO request) {
        return toResponse(enrollmentRepository.save(toEntity(request)));
    }

    public EnrollmentResponseDTO update(Long id, EnrollmentRequestDTO request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found with id: " + id));
        enrollment.setEnrollmentDate(request.enrollmentDate());
        enrollment.setStatus(request.status());
        enrollment.setGrade(request.grade());
        return toResponse(enrollmentRepository.save(enrollment));
    }

    public void delete(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found with id: " + id));
        enrollmentRepository.delete(enrollment);
    }
}