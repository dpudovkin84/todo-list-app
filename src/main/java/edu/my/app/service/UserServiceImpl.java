package edu.my.app.service;

import edu.my.app.entity.Role;
import edu.my.app.repositories.UserRepository;
import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.execptions.UserExistsException;
import edu.my.app.execptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByUsername(username);
        if(user==null){
            throw new UserNotFoundException("User "+username+" was not found");
        }
        return user;
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
    public User getUser(String username) throws UserNotFoundException {
        User user= userRepository.findUserByUsername(username);
        if(user==null){
            throw new UserNotFoundException("User not found");
        }else {
            return user;
        }
    }

    @Override
    public User saveUser(User user) throws UserExistsException {
        if(((user.getId()==null)&&(
        userRepository.findUserByUsername(user.getUsername())==null))||
                ((!(user.getId()==null))&&
                        (userRepository.findById(user.getId())!=null))){
            user=userRepository.save(user);
            System.out.println("User "+user.getUsername()+" " +
                    "was saved");
            return user;

        } else {
            throw new UserExistsException("User already exists");
        }
    }

    @Override
    public String deleteUser(String username) {
        User user=getUser(username);
        if(user!=null){
            userRepository.delete(user);
            return "User "+username+" was deleted";
        }else {
            throw new UserNotFoundException("User was not found");
        }
    }
    @Override
    public String deleteUser(User user) {
        String username=user.getUsername();
        if(user!=null){
            userRepository.delete(user);
            return "User "+username+" was deleted";
        }else {
            throw new UserNotFoundException("User was not found");
        }
    }

    @Override
    public User addTodoToUser(String username,Todo todo) {
        User user=getUser(username);
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
        user.addTodo(todo);
        userRepository.save(user);
        todo.setUser(user);
        return user;
    }

    @Override
    public User createUser() {
        User user=new User("Enter your name");
        Role defaultRole= roleService.getRole("ROLE_USER");
        user.addRole(defaultRole);
        return user;
    }

    @Override
    public Map<String, String> getUserRoleMap(String username) {
        User user = getUser(username);
        List<Role> roles = roleService.getAllRoles();
        Map<String,String> roleMap=new HashMap<>();
//        roleMap.put("username",username);
        for(Role role: roles){
            if(user.getRoles().contains(role)){
                roleMap.put(role.getName(),"enabled");
            }
            else {
                roleMap.put(role.getName(),"disabled");
            }
        }
        return roleMap;
    }
}
