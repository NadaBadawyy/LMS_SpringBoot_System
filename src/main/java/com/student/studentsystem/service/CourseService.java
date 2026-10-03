package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.CourseRequestDTO;
import com.student.studentsystem.dto.CourseResponseDTO;
import com.student.studentsystem.entity.Course;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private CourseResponseDTO toResponse(Course course) {
        return new CourseResponseDTO(course.getId(), course.getCode(), course.getTitle(), course.getDescription(), course.getCredits(), course.getActive());
    }

    private Course toEntity(CourseRequestDTO request) {
        Course course = new Course();
        course.setCode(request.code());
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCredits(request.credits());
        course.setActive(request.active());
        return course;
    }

    public List<CourseResponseDTO> getAll() {
        return courseRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CourseResponseDTO getById(Long id) {
        return courseRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
    }

    public CourseResponseDTO create(CourseRequestDTO request) {
        return toResponse(courseRepository.save(toEntity(request)));
    }

    public CourseResponseDTO update(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
        course.setCode(request.code());
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCredits(request.credits());
        course.setActive(request.active());
        return toResponse(courseRepository.save(course));
    }

    public void delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }
}