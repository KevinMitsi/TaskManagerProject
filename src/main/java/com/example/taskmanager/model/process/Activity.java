package com.example.taskmanager.model.process;


import com.example.taskmanager.exceptions.CompleteException;
import com.example.taskmanager.listas.Cola;
import java.io.Serializable;
import java.util.Objects;

public class Activity implements Serializable, Completable{
    private String id;

    private String description;
    private boolean isMandatory;
    private Cola<Task> tasksList;
    private boolean isComplete;

    public Activity(String id, String description, boolean isMandatory) {
        this.id = id;
        this.tasksList = new Cola<>();
        this.description = description;
        this.isMandatory = isMandatory;
        this.isComplete = false;
    }

    public Activity() {
        tasksList=new Cola<>();
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cola<Task> getTasksList() {
        return tasksList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;
        return Objects.equals(getId(), activity.getId()) || Objects.equals(getDescription(), activity.getDescription()) || Objects.equals(getTasksList(), activity.getTasksList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getTasksList());
    }

    public String getDescription() {
        return description;
    }

    public void setTasksList(Cola<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    @Override
    public void complete() throws CompleteException {
        boolean todasCompletas = true;

        for (Task tarea : getTasksList()) {
            if (!tarea.isComplete()) {
                throw new CompleteException("Is impossible to complete the activity because there are non completed tasks");
            }
        }
        setComplete(todasCompletas);
    }

    @Override
    public String toString() {
        return description;
    }
}
