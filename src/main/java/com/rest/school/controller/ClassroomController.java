package com.rest.school.controller;

import java.util.List;

import javax.validation.Valid;

import com.rest.school.model.Classroom;
import com.rest.school.service.ClassroomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/classroom")
@RestController
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    @GetMapping("/")
    public ResponseEntity<List<Classroom>> getAllClasses() {
        return ResponseEntity.ok(classroomService.getClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable("id") int id) throws NotFoundException {
        return ResponseEntity.ok(classroomService.getClassroom(id));
    }

    @PostMapping("/")
    public ResponseEntity<Classroom> postClassroom(@RequestBody @Valid Classroom entity) {
        return new ResponseEntity<Classroom>(classroomService.createClassroom(entity), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> putClassroom(@PathVariable("id")int id, @RequestBody Classroom classroom) throws NotFoundException{
        return new  ResponseEntity<Classroom>(classroomService.putClasroom(id, classroom), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable("id") int id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}