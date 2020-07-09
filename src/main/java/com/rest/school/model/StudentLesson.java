package com.rest.school.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Table(name = "student_lesson")
@Entity
@Data
public class StudentLesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn
    private Student student;

    @ManyToOne(targetEntity = Lesson.class)
    @JoinColumn
    private Lesson lesson;

    @Column(name = "grade", nullable = false)
    private float grade;
}