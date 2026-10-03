package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.CourseOfferingRequestDTO;
import com.student.studentsystem.dto.CourseOfferingResponseDTO;
import com.student.studentsystem.entity.CourseOffering;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.CourseOfferingRepository;

@Service
public class CourseOfferingService {
    private final CourseOfferingRepository courseOfferingRepository;

    public CourseOfferingService(CourseOfferingRepository courseOfferingRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
    }

    private CourseOfferingResponseDTO toResponse(CourseOffering offering) {
        return new CourseOfferingResponseDTO(offering.getId(), offering.getSectionCode(), offering.getCapacity(), offering.getStatus(), offering.getStartDate(), offering.getEndDate());
    }

    private CourseOffering toEntity(CourseOfferingRequestDTO request) {
        CourseOffering offering = new CourseOffering();
        offering.setSectionCode(request.sectionCode());
        offering.setCapacity(request.capacity());
        offering.setStatus(request.status());
        offering.setStartDate(request.startDate());
        offering.setEndDate(request.endDate());
        return offering;
    }

    public List<CourseOfferingResponseDTO> getAll() {
        return courseOfferingRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CourseOfferingResponseDTO getById(Long id) {
        return courseOfferingRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Course offering not found with id: " + id));
    }

    public CourseOfferingResponseDTO create(CourseOfferingRequestDTO request) {
        return toResponse(courseOfferingRepository.save(toEntity(request)));
    }

    public CourseOfferingResponseDTO update(Long id, CourseOfferingRequestDTO request) {
        CourseOffering offering = courseOfferingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course offering not found with id: " + id));
        offering.setSectionCode(request.sectionCode());
        offering.setCapacity(request.capacity());
        offering.setStatus(request.status());
        offering.setStartDate(request.startDate());
        offering.setEndDate(request.endDate());
        return toResponse(courseOfferingRepository.save(offering));
    }

    public void delete(Long id) {
        CourseOffering offering = courseOfferingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course offering not found with id: " + id));
        courseOfferingRepository.delete(offering);
    }
}