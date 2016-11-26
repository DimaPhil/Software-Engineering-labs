package dao;

import model.Task;
import model.TodoList;

import java.util.List;

/**
 * Created by dimaphil on 26.11.16.
 */
public interface TodoListsDao {
    int addTodoList(TodoList todoList);
    int addTodoToList(int id, Task todo);
    List<TodoList> getTodoLists();
    int setIsDone(int todoId, boolean isDone);
    int deleteTodoList(TodoList todoList);
    int deleteTodo(int todoId);


}
