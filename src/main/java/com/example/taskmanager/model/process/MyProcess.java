package com.example.taskmanager.model.process;

import com.example.taskmanager.controllers.Alerta;
import com.example.taskmanager.exceptions.CompleteException;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public String toString() {
        return nombre;
    }

    @Override
    public void complete() throws CompleteException {
        boolean complete = true;
        for(Activity activity:getTaskList()){
            if (!activity.isComplete()){
                throw new CompleteException("Is impossible to complete the process because there are activities that are not completed");
            }
        }
        Alerta.saltarAlertaInformacion("You completed the activity properly");
        setComplete(complete);
    }
}
