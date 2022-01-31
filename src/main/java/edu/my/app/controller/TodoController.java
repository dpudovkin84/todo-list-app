package edu.my.app.controller;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo todo){
        return todoService.updateTodo(todo);
    }

    @GetMapping("/todos/id/{id}")
    public Todo getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }

    @DeleteMapping("/todos/id/{id}")
    public String deleteTodoById(@PathVariable Long id){
        return todoService.deleteTodo(id);
    }

}
