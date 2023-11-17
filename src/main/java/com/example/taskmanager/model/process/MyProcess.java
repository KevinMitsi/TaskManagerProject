package com.example.taskmanager.model.process;

import com.example.taskmanager.listas.DoubleLinkedList;

import java.io.Serializable;
import java.util.Objects;

public class MyProcess implements Serializable {
    private String id;
    private final DoubleLinkedList<Activity>taskList;

    public MyProcess(String id) {
        this.id = id;
        taskList=new DoubleLinkedList<>();
    }

    public MyProcess() {
        taskList = new DoubleLinkedList<>();
    }

    public String getId() {
        return id;
    }

    public DoubleLinkedList<Activity> getTaskList() {
        return taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyProcess myProcess)) return false;
        return Objects.equals(getId(), myProcess.getId()) || Objects.equals(getTaskList(), myProcess.getTaskList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTaskList());
    }
}
