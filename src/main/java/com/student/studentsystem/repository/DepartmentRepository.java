package com.student.studentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.studentsystem.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
