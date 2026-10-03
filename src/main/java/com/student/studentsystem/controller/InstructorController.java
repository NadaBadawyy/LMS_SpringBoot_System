package com.student.studentsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.studentsystem.dto.CourseOfferingResponseDTO;
import com.student.studentsystem.dto.CourseResponseDTO;
import com.student.studentsystem.dto.InstructorRequestDTO;
import com.student.studentsystem.dto.InstructorResponseDTO;
import com.student.studentsystem.service.CourseOfferingService;
import com.student.studentsystem.service.CourseService;
import com.student.studentsystem.service.InstructorService;

@RestController 
@RequestMapping ("/instructors")
public class InstructorController {
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final CourseOfferingService courseOfferingService;

    public InstructorController(InstructorService instructorService, CourseService courseService, CourseOfferingService courseOfferingService){
        this.instructorService=instructorService;
        this.courseService=courseService;
        this.courseOfferingService=courseOfferingService;
    }
    @GetMapping 
    public List<InstructorResponseDTO> getAllInstructors(){
        return instructorService.getAllInstructors();
    }
    @GetMapping ("/{id}")
    public InstructorResponseDTO getInstructorById(@PathVariable Long id){
        return instructorService.getInstructorById(id);
    }
    @GetMapping ("/{instructorId}/course-offerings")
    public List<CourseOfferingResponseDTO> getCourseOfferingsByInstructorId(@PathVariable Long instructorId){
        return courseOfferingService.getCourseOfferingsByInstructorId(instructorId);
    }

    @GetMapping ("/{instructorId}/courses")
    public List<CourseResponseDTO> getCoursesByInstructorId(@PathVariable Long instructorId){
        return courseService.getCoursesByInstructorId(instructorId);
    }
    @PostMapping 
    public InstructorResponseDTO createInstructor(@RequestBody InstructorRequestDTO instructor){
        return instructorService.createInstructor(instructor);
    }
    @PutMapping ("/{id}")
    public InstructorResponseDTO updateInstructor(@PathVariable Long id,@RequestBody InstructorRequestDTO instructor){
        return instructorService.updateInstructor(id, instructor);  }

    @DeleteMapping ("/{id}")
    public String deleteInstructor(@PathVariable Long id){
        return instructorService.deleteInstructor(id);
    }
    
    


}
