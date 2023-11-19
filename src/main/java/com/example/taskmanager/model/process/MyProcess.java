package com.example.taskmanager.model.process;

import com.example.taskmanager.listas.DoubleLinkedList;

import java.io.Serializable;
import java.util.Objects;

public class MyProcess implements Serializable, Completable {
    private String id;
    private String nombre;
    private DoubleLinkedList<Activity>taskList;
    private boolean isComplete;
    public MyProcess(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        taskList=new DoubleLinkedList<>();
        this.isComplete = false;
    }

    public MyProcess() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTaskList(DoubleLinkedList<Activity> taskList) {
        this.taskList = taskList;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public DoubleLinkedList<Activity> getTaskList() {
        return taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyProcess myProcess)) return false;
        return Objects.equals(getId(), myProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskList());
    }

    @Override
    public void complete() {
        boolean complete = true;
        for(Activity activity:getTaskList()){
            if (!activity.isComplete()){
                complete = false;
                break;
            }
        }
        setComplete(complete);
    }
}
