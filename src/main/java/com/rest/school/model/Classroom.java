package com.rest.school.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "classroom")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Classroom implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name="name",nullable=false)
    private String name;

    @NotNull
    @Column(name="code",unique = true,nullable=false)
    private String code;

    @NotNull
    @Column(name="level",nullable=false)
    private int level;

     //@NotNull
    //@Formula("(select count(*) FROM Student s, Classroom c WHERE s.classroom_id = c.id)")
    //@JsonProperty(access =Access.READ_ONLY)
    @Column(name="student_count")
    @ColumnDefault("0")
    private int studentCount = 0;
    
    @JsonIgnore
    @OneToMany(mappedBy = "classroom",cascade = CascadeType.PERSIST)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom",cascade = CascadeType.REMOVE)
    private List<ClassInventory> classInventoryList;

}