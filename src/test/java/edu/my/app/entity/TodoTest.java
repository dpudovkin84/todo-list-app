package edu.my.app.entity;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    Todo todo;

    @BeforeEach
    public void setUp(){
        todo=new Todo();
    }

    @Test
    void getId() {
        todo.setId(10L);
        assertEquals(10,todo.getId() );
    }

    @Test
    void getDescription() {
    }

    @Test
    void getUser() {
    }
}