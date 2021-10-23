package tests;

import main.old.entity.Task;
import main.old.entity.TodoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoListTest {
    Task task;
    TodoList todoList;
    @BeforeEach
    void setUp() {
        task = new Task("Math Homework");
        todoList = new TodoList();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addTask() {
        todoList.addTask(task);
        assertTrue(todoList.isTaskinList(task));
    }
}