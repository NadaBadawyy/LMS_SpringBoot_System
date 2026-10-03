package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.SubmissionRequestDTO;
import com.student.studentsystem.dto.SubmissionResponseDTO;
import com.student.studentsystem.entity.Submission;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.SubmissionRepository;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    private SubmissionResponseDTO toResponse(Submission submission) {
        return new SubmissionResponseDTO(submission.getId(), submission.getSubmittedAt(), submission.getContent(), submission.getScore(), submission.getFeedback());
    }

    private Submission toEntity(SubmissionRequestDTO request) {
        Submission submission = new Submission();
        submission.setSubmittedAt(request.submittedAt());
        submission.setContent(request.content());
        submission.setScore(request.score());
        submission.setFeedback(request.feedback());
        return submission;
    }

    public List<SubmissionResponseDTO> getAll() {
        return submissionRepository.findAll().stream().map(this::toResponse).toList();
    }

    public SubmissionResponseDTO getById(Long id) {
        return submissionRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Submission not found with id: " + id));
    }

    public SubmissionResponseDTO create(SubmissionRequestDTO request) {
        return toResponse(submissionRepository.save(toEntity(request)));
    }

    public SubmissionResponseDTO update(Long id, SubmissionRequestDTO request) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Submission not found with id: " + id));
        submission.setSubmittedAt(request.submittedAt());
        submission.setContent(request.content());
        submission.setScore(request.score());
        submission.setFeedback(request.feedback());
        return toResponse(submissionRepository.save(submission));
    }

    public void delete(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Submission not found with id: " + id));
        submissionRepository.delete(submission);
    }
}