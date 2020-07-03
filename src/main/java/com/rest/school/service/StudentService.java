package com.rest.school.service;

import java.util.List;

import com.rest.school.model.Student;
import com.rest.school.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;



@Service
public class StudentService {
    
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(int id) throws NotFoundException{
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such Student with id: " + id));
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student student) throws NotFoundException{
        return studentRepository.findById(id).map(studentObj -> {
            studentObj.setName(student.getName());
            studentObj.setGrade(student.getGrade());
            studentObj.setSurname(student.getSurname());
            studentObj.setBirthDate(student.getBirthDate());
            studentObj.setClassroom(student.getClassroom());
            return studentRepository.save(studentObj);
        }).orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));
    }

    public Boolean deleteStudent(int id){
        if(studentRepository.findById(id) != null){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
        
    }

}