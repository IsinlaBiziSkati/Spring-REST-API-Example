package com.rest.school.service;

import java.util.List;

import com.rest.school.model.Lesson;
import com.rest.school.repository.LessonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class LessonService {
    
    @Autowired 
    LessonRepository lessonRepository;

    public List<Lesson> getLessons(){
        return lessonRepository.findAll();
    }

    public Lesson getLesson(int id) throws NotFoundException{
        return lessonRepository.findById(id).orElseThrow(() -> new NotFoundException("Lesson with id " + id + " not found"));
    }

    public Lesson postLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    public Lesson putLesson(int id, Lesson lesson) throws NotFoundException{
        return lessonRepository.findById(id).map(newLessonObj -> {
            newLessonObj.setName(lesson.getName());
            return lessonRepository.save(newLessonObj);
        }).orElseThrow(() -> new NotFoundException("Lesson with id " + id + " not found"));
    }

    public void deleteLesson(int id){
        lessonRepository.deleteById(id);
    }
}