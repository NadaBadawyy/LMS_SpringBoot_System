package com.student.studentsystem.entity;

import java.util.List;

import org.hibernate.annotations.MapKeyCompositeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String title;
    private String description;
    private Integer credits;
    private Boolean active;


    @ManyToOne 
    @JoinColumn (name="dept_id")
    private Department department;
    @ManyToOne
    @JoinColumn (name="instructor_id")
    private Instructor instructor;

    @OneToMany (mappedBy = "course")
    private List<Lesson> lessons;
    public Course() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
}