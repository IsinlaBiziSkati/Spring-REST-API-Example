package com.rest.school.repository;


import com.rest.school.model.StudentLesson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLessonRepository extends JpaRepository<StudentLesson,Integer>{
}