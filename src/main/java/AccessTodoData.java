package main.java;

public class AccessTodoData {
    private TodoList todoList;

    /*
    Initializes the Todolist for the project, with a sample task (for phase0)
     */
    public AccessTodoData() {
        todoList = new TodoList();
        todoList.addTask(new Task("Finish CSC207 Phase 0"));
    }

    public TodoList getTodoList() {
        return todoList;
    }

}
