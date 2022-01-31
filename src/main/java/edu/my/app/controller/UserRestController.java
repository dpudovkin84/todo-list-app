package edu.my.app.controller;


import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/users/name/{userName}")
    public User getUserByName(@PathVariable String userName){
        return userService.getUser(userName);
    }

    @PostMapping("/users/addTodo/{userName}")
    public User addTodo(@PathVariable String userName, @RequestBody Todo todo){
        return userService.addTodoToUser(userName,todo);
    }

    @DeleteMapping("/users/{userName}")
    public String deleteUser(@PathVariable String userName){
       return userService.deleteUser(userName);
    }
}
