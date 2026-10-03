package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.SemesterRequestDTO;
import com.student.studentsystem.dto.SemesterResponseDTO;
import com.student.studentsystem.entity.Semester;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.SemesterRepository;

@Service
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    private SemesterResponseDTO toResponse(Semester semester) {
        return new SemesterResponseDTO(semester.getId(), semester.getName(), semester.getStartDate(), semester.getEndDate());
    }

    private Semester toEntity(SemesterRequestDTO request) {
        Semester semester = new Semester();
        semester.setName(request.name());
        semester.setStartDate(request.startDate());
        semester.setEndDate(request.endDate());
        return semester;
    }

    public List<SemesterResponseDTO> getAll() {
        return semesterRepository.findAll().stream().map(this::toResponse).toList();
    }

    public SemesterResponseDTO getById(Long id) {
        return semesterRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Semester not found with id: " + id));
    }

    public SemesterResponseDTO create(SemesterRequestDTO request) {
        return toResponse(semesterRepository.save(toEntity(request)));
    }

    public SemesterResponseDTO update(Long id, SemesterRequestDTO request) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Semester not found with id: " + id));
        semester.setName(request.name());
        semester.setStartDate(request.startDate());
        semester.setEndDate(request.endDate());
        return toResponse(semesterRepository.save(semester));
    }

    public void delete(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Semester not found with id: " + id));
        semesterRepository.delete(semester);
    }
}