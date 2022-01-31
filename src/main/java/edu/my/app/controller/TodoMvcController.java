package edu.my.app.controller;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.service.TodoService;
import edu.my.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class TodoMvcController {

    private TodoService todoService;
    private UserService userService;

    public TodoMvcController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping("/todo/{id}/edit")
    public String getTodoInfo(@PathVariable("id") long id, Model model){
        Todo todo=todoService.getTodo(id);
        model.addAttribute("todo",todo);
        return "todos/todo-edit";
    }
    @PostMapping("/todo/update/{userId}")
    public String updateTodo(@ModelAttribute("todo") Todo todo,@PathVariable Long userId,
                           Model model){
        User user = userService.getUser(userId);
        user.addTodo(todo);
        userService.saveUser(user);
        Set<Todo> todos=user.getTodos();
        model.addAttribute("todos",todos);
        model.addAttribute("user",user);
        return "users/todo-list";
    }
    @GetMapping("/todo/add/{userId}")
    public String addTodo(@PathVariable Long userId,Model model){
        User user=userService.getUser(userId);
        model.addAttribute("todo",new Todo());
        model.addAttribute("user",user);
        return "todos/todo-add";
    }

}
