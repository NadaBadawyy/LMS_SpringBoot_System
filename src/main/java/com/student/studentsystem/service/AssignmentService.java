package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.AssignmentRequestDTO;
import com.student.studentsystem.dto.AssignmentResponseDTO;
import com.student.studentsystem.entity.Assignment;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.AssignmentRepository;

@Service
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    private AssignmentResponseDTO toResponse(Assignment assignment) {
        return new AssignmentResponseDTO(assignment.getId(), assignment.getTitle(), assignment.getDescription(), assignment.getDueDate(), assignment.getMaxScore());
    }

    private Assignment toEntity(AssignmentRequestDTO request) {
        Assignment assignment = new Assignment();
        assignment.setTitle(request.title());
        assignment.setDescription(request.description());
        assignment.setDueDate(request.dueDate());
        assignment.setMaxScore(request.maxScore());
        return assignment;
    }

    public List<AssignmentResponseDTO> getAll() {
        return assignmentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AssignmentResponseDTO getById(Long id) {
        return assignmentRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Assignment not found with id: " + id));
    }

    public AssignmentResponseDTO create(AssignmentRequestDTO request) {
        return toResponse(assignmentRepository.save(toEntity(request)));
    }

    public AssignmentResponseDTO update(Long id, AssignmentRequestDTO request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Assignment not found with id: " + id));
        assignment.setTitle(request.title());
        assignment.setDescription(request.description());
        assignment.setDueDate(request.dueDate());
        assignment.setMaxScore(request.maxScore());
        return toResponse(assignmentRepository.save(assignment));
    }

    public void delete(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Assignment not found with id: " + id));
        assignmentRepository.delete(assignment);
    }
}