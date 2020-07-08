package com.rest.school.controller;

import java.util.List;

import javax.validation.Valid;

import com.rest.school.model.Student;
import com.rest.school.model.StudentLesson;
import com.rest.school.service.StudentLessonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/studentlesson")
public class StudentLessonController {
    @Autowired
    StudentLessonService studentLessonService;

    @GetMapping("/")
    public ResponseEntity<List<StudentLesson>> getStudentLessons(){
        return ResponseEntity.ok(studentLessonService.getStudentLessons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentLesson> getStudentLesson(@PathVariable("id") int id) throws NotFoundException{
        return ResponseEntity.ok(studentLessonService.getStudentLesson(id));
    }
    @PostMapping("/")
    public ResponseEntity<StudentLesson> postStudentLesson(@RequestBody @Valid StudentLesson studentLesson) {
        return new ResponseEntity<StudentLesson>(studentLessonService.postStudentLesson(studentLesson), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentLesson> putStudentLesson(@PathVariable("id") int id, @RequestBody @Valid StudentLesson studentLesson)throws NotFoundException{
        return new ResponseEntity<StudentLesson>(studentLessonService.putStudentLesson(id, studentLesson),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentLesson(@PathVariable("id") int id){
        studentLessonService.deleteStudentLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}