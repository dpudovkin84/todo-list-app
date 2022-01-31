package edu.my.app.service;

import edu.my.app.repositories.UserRepository;
import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.execptions.UserExistsException;
import edu.my.app.execptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList=userRepository.findAll();
        return userList;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optional=userRepository.findById(id);
        if(optional.isPresent()){
        return optional.get();
        }else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User getUser(String userName) throws UserNotFoundException {
        User user= userRepository.findUserByUserName(userName);
        if(user==null){
            throw new UserNotFoundException("User not found");
        }else {
            return user;
        }
    }

    @Override
    public User saveUser(User user) throws UserExistsException {
        if(((user.getId()==null)&&(
        userRepository.findUserByUserName(user.getUserName())==null))||
                ((!(user.getId()==null))&&
                        (userRepository.findById(user.getId())!=null))){
            user=userRepository.save(user);
            System.out.println("User "+user.getUserName()+" " +
                    "was saved");
            return user;

        } else {
            throw new UserExistsException("User already exists");
        }
    }

    @Override
    public String deleteUser(String userName) {
        User user=getUser(userName);
        if(user!=null){
            userRepository.delete(user);
            return "User "+userName+" was deleted";
        }else {
            throw new UserNotFoundException("User was not found");
        }
    }
    @Override
    public String deleteUser(User user) {
        String userName=user.getUserName();
        if(user!=null){
            userRepository.delete(user);
            return "User "+userName+" was deleted";
        }else {
            throw new UserNotFoundException("User was not found");
        }
    }

    @Override
    public User addTodoToUser(String userName,Todo todo) {
        User user=getUser(userName);
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
        user.addTodo(todo);
        userRepository.save(user);
        todo.setUser(user);
        return user;
    }
}
