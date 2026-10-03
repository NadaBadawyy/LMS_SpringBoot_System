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

import com.student.studentsystem.dto.AssignmentRequestDTO;
import com.student.studentsystem.dto.AssignmentResponseDTO;
import com.student.studentsystem.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) { this.assignmentService = assignmentService; }

    @GetMapping
    public List<AssignmentResponseDTO> getAll() { return assignmentService.getAll(); }

    @GetMapping("/{id}")
    public AssignmentResponseDTO getById(@PathVariable Long id) { return assignmentService.getById(id); }

    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> create(@Valid @RequestBody AssignmentRequestDTO request) {
        AssignmentResponseDTO created = assignmentService.create(request);
        return ResponseEntity.created(URI.create("/assignments/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public AssignmentResponseDTO update(@PathVariable Long id, @Valid @RequestBody AssignmentRequestDTO request) {
        return assignmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}