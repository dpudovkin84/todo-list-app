package edu.my.app.service;

import edu.my.app.entity.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> getAllTodos();
    public Todo getTodo(Long id);
    public String deleteTodo(Long id);
    public Todo updateTodo(Todo todo);
    public Todo createTodo(Todo todo);

}
