/**
 * @author Vincent Kao
 */
package com.vincent.todolist.controller;


import com.vincent.todolist.model.entity.Todo;
import com.vincent.todolist.service.TodoService;
import com.vincent.todolist.util.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Optional;

@Api(tags = "Todo list 相關api")
@RequestMapping("/api")
@RestController
public class TodoController {
    private static final Logger logger
            = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    TodoService todoService;

    @ApiOperation("取得所有待辦事項列表")
    @ApiResponses({
            @ApiResponse(code = 401, message = "沒有權限"),
            @ApiResponse(code = 404, message = "找不到路徑")
    })

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody HashMap<String, String> user) {
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.generateToken(user); // 取得token

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping("/hello")
    public ResponseEntity hello(@RequestHeader("Authorization") String au) {
        String token = au.substring(6);
        JwtToken jwtToken = new JwtToken();
        try {
            jwtToken.validateToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Hello Vince");
    }

    @GetMapping("/todo")
    public Optional<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> todoList = todoService.findById(id);
        return todoList;
    }

    @GetMapping("/todos")
    public ResponseEntity getTodos() {
        Iterable<Todo> todoList = todoService.getTodos();
        //logger.info("Hi...");
        //logger.error("I am an error");
        //logger.warn("Warning!.");
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping("/todos")
    public ResponseEntity createTodo(@RequestBody Todo todo) {
        Integer rlt = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity upadteTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        Boolean rlt = todoService.updateTodo(id, todo);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @ResponseBody
    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id) {
        Boolean rlt = todoService.deleteTodo(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @GetMapping("/todos/{id}/tags")
    public ResponseEntity getTodosByUserId(@PathVariable Integer id) {
        Optional<Todo> todos = todoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }
}
