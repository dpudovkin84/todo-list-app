package edu.my.app.service;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.execptions.UserExistsException;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUser(Long id);
    public User getUser(String name);
    public User saveUser(User user) throws UserExistsException;
    public String deleteUser(String userName);
    public String deleteUser(User user);

    public User addTodoToUser(String userName, Todo todo);

}
