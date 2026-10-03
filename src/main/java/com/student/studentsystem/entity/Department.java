package com.student.studentsystem.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    private Long id;

    private String name;
    
    @OneToMany (mappedBy = "department")
    private List<Student>students;
    @OneToMany (mappedBy = "department")
    private List<Instructor>instructors;
    @OneToMany (mappedBy = "department")
    private List<Course>courses;
    public Department(){
        
    }
    
       public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    

}
