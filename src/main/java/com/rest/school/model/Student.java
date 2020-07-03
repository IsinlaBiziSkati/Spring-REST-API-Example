package com.rest.school.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "name",length = 50)
    @Length(min = 1,max = 50)
    private String name;
    
    @NotNull
    @Column(name = "surname",length = 50)
    @Length(min = 1,max = 50)
    private String surname;

    @JsonFormat(pattern="dd-MM-yyyy",timezone = "Europe/Istanbul")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @NotNull
    @Column(name = "grade",nullable = false)
    private float grade;

    @ManyToOne()
    @JoinColumn(name = "classroom_id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Classroom classroom;
}