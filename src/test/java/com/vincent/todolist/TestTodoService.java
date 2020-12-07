package com.vincent.todolist;

import com.vincent.todolist.model.dao.TodoRepository;
import com.vincent.todolist.model.entity.Todo;
import com.vincent.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class TestTodoService {
    @Autowired
    TodoService todoService;

    @MockBean
    TodoRepository todoRepository;

    @Test
    public void testGetTodos() {
        // [Arrange] 編輯資料
        List<Todo> expectedTodosList = new ArrayList();
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("洗衣服");
        todo.setStatus(1);
        expectedTodosList.add(todo);

        // 定義模擬呼叫todoRepository.findAll() 要回傳的預設結果
        Mockito.when(todoRepository.findAll()).thenReturn(expectedTodosList);

        // [Act]操作todoService.getTodos();
        Iterable<Todo> actualTodoList = todoService.getTodos();

        // [Assert] 預期與實際的資料
        assertEquals(expectedTodosList, actualTodoList);
    }

    @Test
    public void testCreateTodo() {
        // [Arrange] 編輯資料
        Todo toDo = new Todo();
        toDo.setId(1);
        toDo.setTask("丟履歷");
        toDo.setStatus(1);

        // 定義模擬呼叫todoRepository.save(toDo) 要回傳的預設結果
        Mockito.when(todoRepository.save(toDo)).thenReturn(toDo);

        // [Act]操作todoService.createTodo(toDo);
        Integer actualId = todoService.createTodo(toDo);

        // [Assert] 預期與實際的資料
        assertEquals(1, actualId);
    }

    @Test
    public void testUpdateTodoSuccess() {
        // [Arrange] 編輯資料
        Todo toDo = new Todo();
        toDo.setId(1);
        toDo.setTask("丟履歷");
        toDo.setStatus(1);
        Optional<Todo> resTodo = Optional.of(toDo);

        // 定義模擬呼叫todoRepository.findById(id) 要回傳的預設結果
        Mockito.when(todoRepository.findById(1)).thenReturn(resTodo);

        // [Act]操作todoService.updateTodo
        toDo.setStatus(2);
        Boolean actualUpdateRlt = todoService.updateTodo(1, toDo);

        // [Assert] 預期與實際的資料
        assertEquals(true, actualUpdateRlt);
    }

    @Test
    public void testUpdateTodoNotExist() {
        // [Arrange] 編輯資料
        Todo toDo = new Todo();
        toDo.setId(1);
        toDo.setTask("丟履歷");
        toDo.setStatus(1);

        // 定義模擬呼叫todoRepository.findById(id) 要回傳的預設結果
        Mockito.when(todoRepository.findById(100)).thenReturn(Optional.empty());

        // [Act]操作todoService.updateTodo
        toDo.setStatus(2);
        Boolean actualUpdateRlt = todoService.updateTodo(100, toDo);

        // [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }

    @Test
    public void testUpdateTodoOccurException() {
        // 準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setStatus(1);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoRepository.findById(id)，資料庫有id=1的資料
        Mockito.when(todoRepository.findById(100)).thenReturn(resTodo);
        todo.setStatus(2);
        // 模擬呼叫todoRepository.save(todo)時發生NullPointerException例外
        doThrow(NullPointerException.class).when(todoRepository).save(todo);

        // [Act] 實際呼叫操作todoService.updateTodo()
        Boolean actualUpdateRlt = todoService.updateTodo(100, todo);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualUpdateRlt);
    }

    @Test
    public void testDeleteTodoSuccess() {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("丟履歷");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoRepository.findById(id)，模擬資料庫有id=1的資料
        Mockito.when(todoRepository.findById(1)).thenReturn(resTodo);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(1);

        //  [Assert] 預期與實際的資料
        assertEquals(true, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoIdNotExist() {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("丟履歷");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoRepository.findById(id)，並模擬資料庫沒有id=100的資料
        Mockito.when(todoRepository.findById(100)).thenReturn(Optional.empty());

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(100);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }

    @Test
    public void testDeleteTodoOccurException() {
        //準備更改的資料
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTask("丟履歷");
        todo.setStatus(2);
        Optional<Todo> resTodo = Optional.of(todo);

        // 模擬呼叫todoRepository.findById(id)，並模擬資料庫有id=1的資料
        Mockito.when(todoRepository.findById(1)).thenReturn(resTodo);

        // 模擬呼叫todoDao.deleteById(id)，會發生NullPointerException
        doThrow(NullPointerException.class).when(todoRepository).deleteById(1);

        // [Act] 實際呼叫操作todoService.deleteTodo()
        Boolean actualDeleteRlt = todoService.deleteTodo(1);

        //  [Assert] 預期與實際的資料
        assertEquals(false, actualDeleteRlt);
    }
}