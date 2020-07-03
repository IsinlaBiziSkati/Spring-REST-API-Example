package com.rest.school.repository;

import org.springframework.stereotype.Repository;

import com.rest.school.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    
}