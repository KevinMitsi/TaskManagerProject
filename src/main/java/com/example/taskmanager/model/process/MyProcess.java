package com.example.taskmanager.model.process;

import com.example.taskmanager.listas.DoubleLinkedList;

import java.io.Serializable;

public class MyProcess implements Serializable {
    private String id;
    private final DoubleLinkedList<Task>taskList;

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

    public DoubleLinkedList<Task> getTaskList() {
        return taskList;
    }
}
