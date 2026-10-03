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

import com.student.studentsystem.dto.CourseOfferingResponseDTO;
import com.student.studentsystem.dto.SemesterRequestDTO;
import com.student.studentsystem.dto.SemesterResponseDTO;
import com.student.studentsystem.service.CourseOfferingService;
import com.student.studentsystem.service.SemesterService;

@RestController
@RequestMapping("/semesters")
public class SemesterController {
    private final SemesterService semesterService;
    private final CourseOfferingService courseOfferingService;
    public SemesterController(SemesterService semesterService, CourseOfferingService courseOfferingService) {
        this.semesterService = semesterService;
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping
    public List<SemesterResponseDTO> getAll() { return semesterService.getAll(); }

    @GetMapping("/{id}")
    public SemesterResponseDTO getById(@PathVariable Long id) { return semesterService.getById(id); }
    @GetMapping ("/{semesterId}/course-offerings")
    public List<CourseOfferingResponseDTO> getCourseOfferingsBySemesterId(@PathVariable Long
    semesterId){
        return courseOfferingService.getCourseOfferingsBySemesterId(semesterId);
    }
    @PostMapping
    public ResponseEntity<SemesterResponseDTO> create(@Valid @RequestBody SemesterRequestDTO request) {
        SemesterResponseDTO created = semesterService.create(request);
        return ResponseEntity.created(URI.create("/semesters/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public SemesterResponseDTO update(@PathVariable Long id, @Valid @RequestBody SemesterRequestDTO request) {
        return semesterService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        semesterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}