package edu.my.app.service;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.execptions.UserExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();
    public User getUser(Long id);
    public User getUser(String name);
    public User saveUser(User user) throws UserExistsException;
    public String deleteUser(String username);
    public String deleteUser(User user);
    public User addTodoToUser(String username, Todo todo);
    public User createUser();
    public Map<String,String> getUserRoleMap(String username);
}
