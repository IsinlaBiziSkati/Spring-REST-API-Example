package com.rest.school.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "class_inventory")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassInventory implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name",nullable = false,length =50)
    @Length(min =1,max =50)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;
    
    @Column(name = "count")
    private int count;

    @ManyToOne()
    @JoinColumn(name = "classroom_id",nullable =false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Classroom classroom;
}