package com.rest.school.service;

import java.util.List;

import com.rest.school.model.StudentLesson;
import com.rest.school.repository.StudentLessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class StudentLessonService {
    @Autowired
    StudentLessonRepository studentLessonRepository;

    public List<StudentLesson> getStudentLessons() {
        return studentLessonRepository.findAll();
    }

    public StudentLesson getStudentLesson(int id) throws NotFoundException{
        return studentLessonRepository.findById(id).orElseThrow(()-> new NotFoundException("The key not found"));
    }

    public StudentLesson postStudentLesson(StudentLesson studentLesson){
        return studentLessonRepository.save(studentLesson);
    }
    public StudentLesson putStudentLesson(int id, StudentLesson studentLesson) throws NotFoundException{
        return studentLessonRepository.findById(id).map(studentLessonObj -> {
            studentLessonObj.setStudent(studentLesson.getStudent());
            studentLessonObj.setLesson(studentLesson.getLesson());
            studentLessonObj.setGrade(studentLesson.getGrade());
            return studentLessonRepository.save(studentLessonObj);
        }).orElseThrow(() -> new NotFoundException("Lesson with id "+ id +"is not found"));
    }

    public void deleteStudentLesson(int id){
        studentLessonRepository.deleteById(id);
    }
}