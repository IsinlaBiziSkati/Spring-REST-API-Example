package com.rest.school.service;

import java.util.List;
import java.util.Optional;

import com.rest.school.model.Classroom;
import com.rest.school.model.Student;
import com.rest.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;

@Service
public class StudentService {
    
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassroomService classroomService;

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(int id) throws NotFoundException{
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no such Student with id: " + id));
    }

    public Student createStudent(Student student) throws NotFoundException{
        Student createdStudent = studentRepository.save(student);
        classroomService.calculateStudentCount(createdStudent.getClassroom());
        return createdStudent;
    }

    public Student updateStudent(int id, Student student) throws NotFoundException{
        Optional<Student> studentInRepository = studentRepository.findById(id);
        Classroom classroom = studentInRepository.get().getClassroom() ;

        Student updatedStudent = studentInRepository.map(studentObj -> {
            studentObj.setName(student.getName());
            studentObj.setGrade(student.getGrade());
            studentObj.setSurname(student.getSurname());
            studentObj.setBirthDate(student.getBirthDate());
            studentObj.setClassroom(student.getClassroom());
            return studentRepository.save(studentObj);
        }).orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        if(classroom.getId() != updatedStudent.getClassroom().getId()){
            classroomService.calculateStudentCount(classroom);
            classroomService.calculateStudentCount(updatedStudent.getClassroom());
        }

        return updatedStudent;
    }

    public void deleteStudent(int id) throws NotFoundException{
        Student studentToDelete = studentRepository.findById(id).get();
        studentRepository.delete(studentToDelete);
        classroomService.calculateStudentCount(studentToDelete.getClassroom());
    }

    public int getStudentCount(int classroomId){
        return studentRepository.getStudentCoun(classroomId);
    }

}