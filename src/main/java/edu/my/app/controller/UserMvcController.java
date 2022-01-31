package edu.my.app.controller;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
public class UserMvcController {

    private UserService userService;

    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "users/user-list";
    }

    @GetMapping("/user/todos/{id}")
    public String getTodoList(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        Set<Todo> todos = user.getTodos();
        model.addAttribute("todos", todos);
        model.addAttribute("user", user);
        return "users/todo-list";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users/user-add";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute User user) {
        User userTemp = userService.saveUser(user);
        System.out.println(userTemp);
        return "redirect:/users";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "users/user-edit";
    }
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/users";
    }
}
