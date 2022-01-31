package edu.my.app.service;

import edu.my.app.repositories.TodoRepository;
import edu.my.app.entity.Todo;
import edu.my.app.execptions.TodoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodo(Long id) {
      Optional<Todo> optional=todoRepository.findById(id);
      if(optional.isPresent()){
          return optional.get();
      }else {
          throw new TodoNotFoundException("Todo was not found");
      }
    }

    @Override
    public String deleteTodo(Long id) {
        Todo todo=getTodo(id);
        if(todo!=null){
            todoRepository.delete(todo);
            return "Todo with ID "+id+" was deleted";
        }else {
           throw new TodoNotFoundException("Todo was not found");
        }
    }

    @Override
    public Todo updateTodo(Todo todo) {
        if((getTodo(todo.getId())!=null)&&
                (getTodo(todo.getId())!=null)){
            todo.setUser(getTodo(todo.getId()).getUser());
            return todoRepository.save(todo);
        }else {
            throw new TodoNotFoundException("Todo was not found");
        }
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
