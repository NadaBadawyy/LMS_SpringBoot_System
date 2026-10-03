package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.LessonRequestDTO;
import com.student.studentsystem.dto.LessonResponseDTO;
import com.student.studentsystem.entity.Lesson;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.LessonRepository;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    private LessonResponseDTO toResponse(Lesson lesson) {
        return new LessonResponseDTO(lesson.getId(), lesson.getTitle(), lesson.getDescription(), lesson.getLessonOrder(), lesson.getContentUrl(), lesson.getDurationMinutes());
    }

    private Lesson toEntity(LessonRequestDTO request) {
        Lesson lesson = new Lesson();
        lesson.setTitle(request.title());
        lesson.setDescription(request.description());
        lesson.setLessonOrder(request.lessonOrder());
        lesson.setContentUrl(request.contentUrl());
        lesson.setDurationMinutes(request.durationMinutes());
        return lesson;
    }

    public List<LessonResponseDTO> getAll() {
        return lessonRepository.findAll().stream().map(this::toResponse).toList();
    }

    public LessonResponseDTO getById(Long id) {
        return lessonRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Lesson not found with id: " + id));
    }

    public LessonResponseDTO create(LessonRequestDTO request) {
        return toResponse(lessonRepository.save(toEntity(request)));
    }

    public LessonResponseDTO update(Long id, LessonRequestDTO request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson not found with id: " + id));
        lesson.setTitle(request.title());
        lesson.setDescription(request.description());
        lesson.setLessonOrder(request.lessonOrder());
        lesson.setContentUrl(request.contentUrl());
        lesson.setDurationMinutes(request.durationMinutes());
        return toResponse(lessonRepository.save(lesson));
    }

    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Lesson not found with id: " + id));
        lessonRepository.delete(lesson);
    }
}