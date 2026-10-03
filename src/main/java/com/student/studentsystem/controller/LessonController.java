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

import com.student.studentsystem.dto.LessonRequestDTO;
import com.student.studentsystem.dto.LessonResponseDTO;
import com.student.studentsystem.service.LessonService;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) { this.lessonService = lessonService; }

    @GetMapping
    public List<LessonResponseDTO> getAll() { return lessonService.getAll(); }

    @GetMapping("/{id}")
    public LessonResponseDTO getById(@PathVariable Long id) { return lessonService.getById(id); }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> create(@Valid @RequestBody LessonRequestDTO request) {
        LessonResponseDTO created = lessonService.create(request);
        return ResponseEntity.created(URI.create("/lessons/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public LessonResponseDTO update(@PathVariable Long id, @Valid @RequestBody LessonRequestDTO request) {
        return lessonService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}