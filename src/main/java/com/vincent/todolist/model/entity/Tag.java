package com.vincent.todolist.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    public String tag;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    @JsonBackReference
    Set<Todo> todos;
}
