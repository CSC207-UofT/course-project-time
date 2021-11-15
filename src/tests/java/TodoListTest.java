

import entity.Task;
import entity.TodoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListTest {
    Task task;
    TodoList todoList;
    @BeforeEach
    void setUp() {
        task = new Task(0, "Math Homework");
        todoList = new TodoList();
    }

    @Test
    void addTask() {
        todoList.addTask(task);
        assertTrue(todoList.isTaskinList(task));
    }
}