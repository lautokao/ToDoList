/**
 * @author Vincent Kao
 */
package com.vincent.todolist.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id = 1;

    @Column(name = "task")
    String task = "";

    @Column(name = "status", insertable = false, columnDefinition = "int default 1")
    Integer status = 1;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    Date createTime = new Date();

    @LastModifiedDate
    @Column(nullable = false)
    Date updateTime = new Date();

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
////    @JoinTable(name = "todos_tag",
////            joinColumns = {
////                    @JoinColumn(name = "todo_id", referencedColumnName = "id",
////                            nullable = false, updatable = false)},
////            inverseJoinColumns = {
////                    @JoinColumn(name = "tag_id", referencedColumnName = "id",
////                            nullable = false, updatable = false)})
//    @JoinTable(name = "todos_tag", joinColumns = {@JoinColumn(name = "tag_id")}, inverseJoinColumns = {@JoinColumn(name = "todo_id")})
//    //@JsonIgnoreProperties("todo")
//    @JsonManagedReference
//    Set<Tag> tags = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "todos_tag", joinColumns = {@JoinColumn(name = "todo_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    Set<Tag> tags = new HashSet<>();
}