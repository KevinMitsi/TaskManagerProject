package com.example.taskmanager.model.person;

import com.example.taskmanager.listas.DoubleLinkedList;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;

import java.io.Serializable;
import java.util.*;

public class Common implements Serializable {
    private String name;
    private String id;
    private String email;
    private Map<String, MyProcess> processes;

    public Common(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.processes = new HashMap<>();
    }

    public Common() {}

    //----------------------Getters Setters and Override code----------------------------------------------------------


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, MyProcess> getProcesses() {
        return processes;
    }

    public void setProcesses(Map<String, MyProcess> processes) {
        this.processes = processes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Common common)) return false;
        return Objects.equals(getId(), common.getId()) || Objects.equals(getEmail(), common.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }


    //----------------------Métodos de búsqueda----------------------------------------------------------


    public ArrayList<Task> searchTask(String word) {
        ArrayList<Task> result = new ArrayList<>();
        for (MyProcess process : processes.values()) {
            searchTaskRecursive(process, word, result);
        }
        return result;
    }

    private void searchTaskRecursive(MyProcess process, String word, ArrayList<Task> result) {
        for (Activity activities : process.getTaskList()) {
            for (Task task : activities.getTasksList()) {
                if (task.getDescription().contains(word)) {
                    result.add(task);
                }
            }
        }
    }

    public ArrayList<Activity> searchActivity(String word) {
        ArrayList<Activity> result = new ArrayList<>();
        for (MyProcess process : processes.values()) {
            searchActivityRecursive(process, word, result);
        }
        return result;
    }

    private void searchActivityRecursive(MyProcess process, String word, ArrayList<Activity> result) {
        for (Activity activities : process.getTaskList()) {
            if (activities.getDescription().contains(word)) {
                result.add(activities);
            }
        }
    }

    @Override
    public String toString() {
        return name +" "+id;


    }
}
