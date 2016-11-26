package model;

/**
 * Created by dimaphil on 26.11.16.
 */
public class Task {
    private int id;
    private int listId;
    private String description;
    private boolean isDone;

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    public Task(int id, int listId, String description, boolean isDone) {
        this.id = id;
        this.listId = listId;
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

}
