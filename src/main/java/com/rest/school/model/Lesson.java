package com.rest.school.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="lesson")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson implements Serializable{/**
	 *
	 */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name="name",nullable=false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson", cascade=CascadeType.ALL)
    private List<StudentLesson> studentLessons;
}