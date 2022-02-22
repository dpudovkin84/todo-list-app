package edu.my.app.controller;

import edu.my.app.entity.Role;
import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.service.RoleService;
import edu.my.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class UserMvcController {

    private UserService userService;
    private RoleService roleService;

    public UserMvcController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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

    @GetMapping("/user/{username}/todos/")
    public String getUserTodoList(@PathVariable("username") String username, Model model) {
        User user = userService.getUser(username);
        Set<Todo> todos = user.getTodos();
        model.addAttribute("todos", todos);
        model.addAttribute("user", user);
        return "users/todo-list";
    }

    @GetMapping("/user/add")
    public String addUser(Model model) {
        User user= userService.createUser();
        model.addAttribute("user", user);
        return "users/user-add";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute User user) {
        User userTemp = userService.saveUser(user);
        System.out.println(userTemp);
        return "redirect:/";
    }

    @GetMapping("/user/edit/{username}")
    public String editUser(@PathVariable("username") String username, Model model) {
        User user = userService.getUser(username);
        List<Role> roles=roleService.getAllRoles();
        model.addAttribute("user", user);
        return "users/user-edit";
    }
    @GetMapping("/user/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        userService.deleteUser(user);
        return "redirect:/users";
    }
    @GetMapping("/user/edit/role/{username}")
    public String editUserRole(@PathVariable("username") String username, Model model) {
        Map<String,String> roleMap=userService.getUserRoleMap(username);
        model.addAttribute("roleMap", roleMap);
        model.addAttribute("username",username);
        return "users/user-edit-role";
    }

}
