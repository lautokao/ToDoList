package com.vincent.todolist.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "gender", insertable = false, columnDefinition = "int default 1")
    Integer gender = 1;

    @Column(name = "password")
    public String password;

    @CreatedDate
    @Column(updatable = false)
    Date createTime = new Date();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    Set<Todo> todos;
}
