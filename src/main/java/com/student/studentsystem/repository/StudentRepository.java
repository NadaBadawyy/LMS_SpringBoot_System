package com.student.studentsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.studentsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {


        @Query ("""
                Select s from Student s
        Where((:name IS NULL
        OR Lower(s.name) LIKE LOWER(Concat('%', :name,'%'))

        )
        AND
        (:deptId IS NULL

        OR s.department.id= :deptId)) """)
       
        public Page<Student> searchStudents(@Param("name") String name, @Param("deptId") Long departmentId,
                        Pageable page);

}
