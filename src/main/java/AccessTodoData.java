package main.java;

public class AccessTodoData {
    private Calendar calendar;
    private TodoList todoList;
    /*
    Initalizes the cTodolist for the project, with a sample task
     */
    public AccessTodoData()
    {
        todoList = new TodoList();
        todoList.addTask(new Task("Finish CSC207 Phase 0"));
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

}
