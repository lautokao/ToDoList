/**
 * @author Vincent Kao
 */
package com.vincent.todolist.controller;


import com.vincent.todolist.model.entity.Todo;
import com.vincent.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/thymeleaf")
@Controller
public class TodoThymeleafController {

    @Autowired
    TodoService todoService;

    @GetMapping("/todo")
    public Iterable<Todo> getTodoList() {
        Iterable<Todo> todoList = todoService.getTodos();
        return todoList;
    }

    @GetMapping("/todos")
    public String getTodos(Model model) {
        Iterable<Todo> todoList = todoService.getTodos();
        model.addAttribute("todolist", todoList);
        Todo todo = new Todo();
        model.addAttribute("todoObject", todo);
        return "todolist";
    }

    @PostMapping("/todos")
    public String createTodo(@ModelAttribute Todo todo, Model model) {
        todoService.createTodo(todo);
        Iterable<Todo> allTodoList = getTodoList();
        Todo emptyTodo = new Todo();
        model.addAttribute("todolist", allTodoList);
        model.addAttribute("todoObject", emptyTodo);
        return "redirect:/thymeleaf/todos";
    }

    //必須加ResponseBody否則return了非json的格式
    //thymeleaf模板無法解析這個回傳的東西。templete engine看不懂回傳值
    @ResponseBody
    @PutMapping("/todos/{id}")
    public void upadteTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        todoService.updateTodo(id, todo);
    }

    @ResponseBody
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
    }
}
