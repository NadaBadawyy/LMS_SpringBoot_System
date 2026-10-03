package com.student.studentsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate enrollmentDate;
    private String status;
    private BigDecimal grade;
    @ManyToOne
    @JoinColumn (name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "courseoffering_id")
    private CourseOffering courseOffering;

    public Enrollment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getGrade() { return grade; }
    public void setGrade(BigDecimal grade) { this.grade = grade; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public CourseOffering getCourseOffering() { return courseOffering; }
    public void setCourseOffering(CourseOffering courseOffering) { this.courseOffering = courseOffering; }
}