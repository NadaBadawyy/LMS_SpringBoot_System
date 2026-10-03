package com.student.studentsystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.studentsystem.dto.CourseOfferingRequestDTO;
import com.student.studentsystem.dto.CourseOfferingResponseDTO;
import com.student.studentsystem.entity.CourseOffering;

import com.student.studentsystem.exceptions.NotFoundException;
import com.student.studentsystem.repository.CourseOfferingRepository;
import com.student.studentsystem.repository.CourseRepository;
import com.student.studentsystem.repository.InstructorRepository;
import com.student.studentsystem.repository.SemesterRepository;

@Service
public class CourseOfferingService {
    private final CourseOfferingRepository courseOfferingRepository;
    private final CourseRepository courseRepository;
    private final SemesterRepository semesterRepository;
    private final InstructorRepository instructorRepository;

    public CourseOfferingService(CourseOfferingRepository courseOfferingRepository, CourseRepository courseRepository, SemesterRepository semesterRepository, InstructorRepository instructorRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
        this.instructorRepository = instructorRepository;
    }

    private CourseOfferingResponseDTO toResponse(CourseOffering offering) {
        return new CourseOfferingResponseDTO(offering.getId(), offering.getSectionCode(), offering.getCapacity(), offering.getStatus(), offering.getStartDate(), offering.getEndDate(),offering.getCourse().getTitle(),offering.getSemester().getName(),offering.getInstructor().getName());
    }

    private CourseOffering toEntity(CourseOfferingRequestDTO request) {
        CourseOffering offering = new CourseOffering();
        offering.setSectionCode(request.sectionCode());
        offering.setCapacity(request.capacity());
        offering.setStatus(request.status());
        offering.setStartDate(request.startDate());
        offering.setEndDate(request.endDate());
        offering.setCourse(courseRepository.findById(request.courseId())
                .orElseThrow(() -> new NotFoundException("Course not found with id: " + request.courseId())));
        offering.setSemester(semesterRepository.findById(request.semesterId())
                .orElseThrow(() -> new NotFoundException("Semester not found with id: " + request.semesterId())));
        offering.setInstructor(instructorRepository.findById(request.instructorId())
                .orElseThrow(() -> new NotFoundException("Instructor not found with id: " + request.instructorId())));

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
    public List<CourseOfferingResponseDTO> getCourseOfferingsByInstructorId(Long instructorId) {
        return courseOfferingRepository.getCourseOfferingsByInstructorId(instructorId).stream()
                .map(this::toResponse)
                .toList();
    }
    public List<CourseOfferingResponseDTO> getCourseOfferingsByCourseId(Long courseId) {
        return courseOfferingRepository.getCourseOfferingsByCourseId(courseId).stream()
                .map(this::toResponse)
                .toList();  }
    public List<CourseOfferingResponseDTO> getCourseOfferingsBySemesterId(Long semesterId) {
        return courseOfferingRepository.getCourseOfferingsBySemesterId(semesterId).stream()

                .map(this::toResponse)
                .toList();  }
                
}