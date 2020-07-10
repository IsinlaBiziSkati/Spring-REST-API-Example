package com.rest.school.service;

import java.util.List;


import com.rest.school.model.Classroom;
import com.rest.school.repository.ClassroomRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClassroomService {
    
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentService studentService;

    //Essential CRUD operations
    public List<Classroom> getClassrooms() {
        return classroomRepository.findAll();
    }

    public Classroom getClassroom(int id) throws NotFoundException {
        return classroomRepository.findById(id).orElseThrow(() -> new NotFoundException("Could not find Classroom : "+id));
    }
    
    public Classroom createClassroom(Classroom classroom){
        classroom.setStudentCount(0);
        return classroomRepository.save(classroom);
    }

    public Classroom putClasroom(int id, Classroom classroom) throws NotFoundException{
        return classroomRepository.findById(id).map(classroomObject -> {
            classroomObject.setName(classroom.getName());
            classroomObject.setCode(classroom.getCode());
            classroomObject.setLevel(classroom.getLevel());
            classroomObject.setStudentCount(classroom.getStudentCount());
            return classroomRepository.save(classroomObject);
        }).orElseThrow(() -> new NotFoundException("There is such no Classroom in database" + id));
    }

    public void deleteClassroom(int id) {
        classroomRepository.deleteById(id);
    }

    //Extra Methods
    public void calculateStudentCount(Classroom classroom) throws NotFoundException{
        Classroom classroomToUpdate = getClassroom(classroom.getId());
        classroomToUpdate.setStudentCount(studentService.getStudentCount(classroom.getId()));
        putClasroom(classroomToUpdate.getId(), classroomToUpdate);
    }
}