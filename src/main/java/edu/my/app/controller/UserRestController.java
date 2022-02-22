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

    @GetMapping("/users/name/{username}")
    public User getUserByName(@PathVariable String username){
        return userService.getUser(username);
    }

    @PostMapping("/users/addTodo/{username}")
    public User addTodo(@PathVariable String username, @RequestBody Todo todo){
        return userService.addTodoToUser(username,todo);
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username){
       return userService.deleteUser(username);
    }
}
