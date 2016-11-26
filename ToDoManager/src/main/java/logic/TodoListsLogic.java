package logic;

import dao.TodoListsDao;
import model.Task;
import model.TodoList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by dimaphil on 26.11.16.
 */
public class TodoListsLogic {
    public static Optional<TodoList> getTodoListByName(List<TodoList> lists, String name) {
        if (lists == null || lists.isEmpty()) {
            return Optional.empty();
        }
        List<TodoList> tmp = lists.
                stream().
                filter(x -> x.getName().equals(name)).
                collect(Collectors.toList());
        if (tmp.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(tmp.get(0));
    }

    public static List<String> getListsNames(List<TodoList> lists) {
        return lists.stream().map(TodoList::getName).collect(Collectors.toList());
    }

    public static void setDone(String name, String[] selected, TodoListsDao dao) {
        Optional<TodoList> list = TodoListsLogic.getTodoListByName(dao.getTodoLists(), name);
        if (list.isPresent()) {
            for (Task todo: list.get()) {
                int todoId = todo.getId();
                boolean has = false;
                for (String s : selected) {
                    if (Integer.parseInt(s) == todoId) {
                        has = true;
                        break;
                    }
                }
                dao.setIsDone(todoId, has);
            }
        }
    }
}
