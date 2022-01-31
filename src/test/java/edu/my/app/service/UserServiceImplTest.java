package edu.my.app.service;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.execptions.UserExistsException;
import edu.my.app.execptions.UserNotFoundException;
import edu.my.app.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {


    UserService userService;
    @Mock
    UserRepository userRepository;

    User user1;
    User user2;
    User user3;
    Todo todo1;
    Todo todo2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService=new UserServiceImpl(userRepository);
        user1=new User("Mark");
        user2=new User("Tacit");
        user2.setId(2L);
        user3=new User("Georg");
        List<User> usersData=new ArrayList<>();
        usersData.add(user1);
        usersData.add(user2);
        usersData.add(user3);
        todo1=new Todo("TestJob1","High");
        todo2=new Todo("TestJob2","Low");
        when(userRepository.findAll()).thenReturn(usersData);
            when(userRepository.findUserByUserName("Tacit")).
                thenReturn(user2);
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.save(user2)).thenReturn(user2);
        when(userRepository.save(user1)).thenReturn(user1);
    }

    @Test
    void getAllUsers() {
        List<User> userList=userService.getAllUsers();
        assertEquals(userList.size(),3);
        verify(userRepository,times(1)).
                findAll();
    }

    @Test
    void getUser() {
    assertEquals(userService.getUser("Tacit"),user2);
    assertEquals(userService.getUser(2L),user2);
    verify(userRepository,times(1)).findUserByUserName("Tacit");
    verify(userRepository,times(1)).findById(2L);
    Exception exception=assertThrows(UserNotFoundException.class,()->{
        userService.getUser("noname");});
    String expectedMessage="User not found";
    String actualMessage=exception.getMessage();
    assertEquals(expectedMessage,actualMessage);
    }



    @Test
    void addTodoToUser() {
        ArgumentCaptor<User> argumentCaptor=ArgumentCaptor.forClass(User.class);
        userService.addTodoToUser("Tacit",todo1);
        assertTrue(user2.getTodos().contains(todo1));
        verify(userRepository,times(1)).findUserByUserName("Tacit");
        verify(userRepository,times(1)).save(user2);
        verify(userRepository,times(1)).save(argumentCaptor.capture());
        User capturedUser=argumentCaptor.getValue();
        assertTrue(capturedUser==user2);
    }
    @Test
    void saveUser() {
    userService.saveUser(user1);
        Exception exception=assertThrows(UserExistsException.class,()->{
            userService.saveUser(user2);});
    }

    @Test
    void deleteUser(){
        userService.deleteUser("Tacit");
        verify(userRepository,times(1)).delete(user2);
    }



}