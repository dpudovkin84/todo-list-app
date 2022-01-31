package edu.my.app.controller;

import edu.my.app.entity.Todo;
import edu.my.app.entity.User;
import edu.my.app.repositories.UserRepository;
import edu.my.app.service.UserService;
import edu.my.app.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserRestControllerTest {

    @Mock
    private UserService userService;

    UserRestController userRestController;

    User user1;
    User user2;
    User user3;
    Todo todo1;
    Todo todo2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRestController=new UserRestController(userService);
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
        when(userService.getAllUsers()).thenReturn(usersData);

    }



    @Test
    void getAllUsers() {
        List<User> userList=new ArrayList<>();
        userList=userRestController.getAllUsers();
        assertEquals(userList.size(),3);
        verify(userService,times(1)).getAllUsers();

    }

    @Test
    void addUser() {
        userRestController.addUser(user1);
    verify(userService,times(1)).saveUser(user1);
    }

    @Test
    void getUserByName() {

    }

    @Test
    void addTodo() {
    }

    @Test
    void deleteUser() {
    }
    @Test
    public void mockMVC(){
        MockMvc mockMvc= MockMvcBuilders.
                standaloneSetup(userRestController).build();

        try {
           MvcResult requestResult=
                   mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                    .andExpect(status().isOk())
                    .andReturn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}