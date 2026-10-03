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

import com.student.studentsystem.dto.SubmissionRequestDTO;
import com.student.studentsystem.dto.SubmissionResponseDTO;
import com.student.studentsystem.service.SubmissionService;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) { this.submissionService = submissionService; }

    @GetMapping
    public List<SubmissionResponseDTO> getAll() { return submissionService.getAll(); }

    @GetMapping("/{id}")
    public SubmissionResponseDTO getById(@PathVariable Long id) { return submissionService.getById(id); }

    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> create(@Valid @RequestBody SubmissionRequestDTO request) {
        SubmissionResponseDTO created = submissionService.create(request);
        return ResponseEntity.created(URI.create("/submissions/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public SubmissionResponseDTO update(@PathVariable Long id, @Valid @RequestBody SubmissionRequestDTO request) {
        return submissionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        submissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}