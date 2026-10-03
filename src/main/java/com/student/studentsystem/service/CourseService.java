package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.CourseRequestDTO;
import com.student.studentsystem.dto.CourseResponseDTO;
import com.student.studentsystem.entity.Course;
import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.CourseRepository;
import com.student.studentsystem.repository.DepartmentRepository;
import com.student.studentsystem.repository.InstructorRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.instructorRepository = instructorRepository;
    }

    private CourseResponseDTO toResponse(Course course) {
        return new CourseResponseDTO(course.getId(), course.getCode(), course.getTitle(), course.getDescription(), course.getCredits(), course.getActive(), course.getDepartment().getName(), course.getInstructor().getName());
    }

    private Course toEntity(CourseRequestDTO request) {
        Course course = new Course();
        course.setCode(request.code());
        course.setTitle(request.title());
        course.setDescription(request.description());
        course.setCredits(request.credits());
        course.setActive(request.active());
        course.setDepartment(departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + request.departmentId())));
        course.setInstructor(instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + request.instructorId())));
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
        departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new NotFoundException("Department not found with id: " + request.departmentId()));
        instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + request.instructorId()));
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
    public List<CourseResponseDTO> getCoursesByDepartmentId(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId).stream().map(this::toResponse).toList();
    }
    public List<CourseResponseDTO> getCoursesByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId).stream().map(this::toResponse).toList();
    }
}