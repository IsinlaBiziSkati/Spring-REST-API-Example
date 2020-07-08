package com.rest.school.controller;

import java.util.List;

import javax.validation.Valid;

import com.rest.school.model.Lesson;
import com.rest.school.service.LessonService;

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
@RequestMapping("/lesson")
public class LessonController {
    @Autowired 
    LessonService lessonService;

    @GetMapping("/")
    public ResponseEntity<List<Lesson>> getLessons(){
        return ResponseEntity.ok(lessonService.getLessons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable("id") int id) throws NotFoundException{
        return ResponseEntity.ok(lessonService.getLesson(id));
    }

    @PostMapping("/")
    public ResponseEntity<Lesson> postLesson(@RequestBody @Valid Lesson lesson){
        return new ResponseEntity<Lesson>(lessonService.postLesson(lesson),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> putLesson(@RequestBody @Valid Lesson lesson, @PathVariable("id") int id) throws NotFoundException {
        return new ResponseEntity<Lesson>(lessonService.putLesson(id, lesson),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable("id") int id){
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}