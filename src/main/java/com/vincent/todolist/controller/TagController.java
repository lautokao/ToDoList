package com.vincent.todolist.controller;

import com.vincent.todolist.model.entity.Tag;
import com.vincent.todolist.model.entity.Todo;
import com.vincent.todolist.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

/**
 * @author Vincent
 */
@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags/{id}/todos")
    public ResponseEntity getTodosByTagId(@PathVariable Integer id) {
        Optional<Tag> tags = tagService.findById(id);
        if (!tags.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        Tag tag = tags.get();
        Set<Todo> todos = tag.getTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }
}
