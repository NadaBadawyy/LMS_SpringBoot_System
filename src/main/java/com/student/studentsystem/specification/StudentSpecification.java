package com.student.studentsystem.specification;

import org.springframework.data.jpa.domain.Specification;

import com.student.studentsystem.entity.Student;

public class StudentSpecification {
    public static Specification<Student> hasName(String name) {
        return (root, query, criteriaBuilder) -> {

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");

        };
    }

    public static Specification<Student> hasDepartment(Long deptId){
        return (root,query, criteriaBuilder)->
        criteriaBuilder.equal(root.get("department").get("id"), deptId);
    }

}
