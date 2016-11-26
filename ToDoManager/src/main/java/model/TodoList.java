package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by nikita on 09.11.16.
 */
public class TodoList implements Iterable<Task> {
    private List<Task> todoList;
    private String name;
    private int id;

    public TodoList(int id, List<Task> todoList, String name) {
        this.todoList = todoList;
        this.name = name;
        this.id = id;
    }

    public TodoList(int id, String name) {
        this.name = name;
        this.todoList = new ArrayList<Task>();
        this.id = id;
    }

    public TodoList(String name) {
        this.name = name;
        this.todoList = new ArrayList<>();
    }

    public TodoList() {
        this.todoList = new ArrayList<Task>();
    }

    public List<Task> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Task> todoList) {
        this.todoList = todoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Iterator<Task> iterator() {
        return todoList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        todoList.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return todoList.spliterator();
    }
}
