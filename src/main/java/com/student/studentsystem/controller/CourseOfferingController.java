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

import com.student.studentsystem.dto.CourseOfferingRequestDTO;
import com.student.studentsystem.dto.CourseOfferingResponseDTO;
import com.student.studentsystem.service.CourseOfferingService;

@RestController
@RequestMapping("/course-offerings")
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    public CourseOfferingController(CourseOfferingService courseOfferingService) { this.courseOfferingService = courseOfferingService; }

    @GetMapping
    public List<CourseOfferingResponseDTO> getAll() { return courseOfferingService.getAll(); }

    @GetMapping("/{id}")
    public CourseOfferingResponseDTO getById(@PathVariable Long id) { return courseOfferingService.getById(id); }

    @PostMapping
    public ResponseEntity<CourseOfferingResponseDTO> create(@Valid @RequestBody CourseOfferingRequestDTO request) {
        CourseOfferingResponseDTO created = courseOfferingService.create(request);
        return ResponseEntity.created(URI.create("/course-offerings/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public CourseOfferingResponseDTO update(@PathVariable Long id, @Valid @RequestBody CourseOfferingRequestDTO request) {
        return courseOfferingService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseOfferingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}