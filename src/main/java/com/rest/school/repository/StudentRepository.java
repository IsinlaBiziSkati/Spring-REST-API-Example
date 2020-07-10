package com.rest.school.repository;

import org.springframework.stereotype.Repository;

import com.rest.school.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT COUNT(std) FROM Student std WHERE std.classroom.id =?1")
    int getStudentCoun(int classroomId);
}