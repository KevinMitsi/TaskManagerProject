package com.example.taskmanager.model.principal;

import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.listas.SimpleLinkedList;
import com.example.taskmanager.model.person.User;
import com.example.taskmanager.model.process.MyProcess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TaskManager implements Serializable {
    private final Map<String, User>userMap;
    private final SimpleLinkedList<MyProcess>processes;
    public TaskManager(){
        userMap=new HashMap<>();
        processes = new SimpleLinkedList<>();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public SimpleLinkedList<MyProcess> getProcesses() {
        return processes;
    }

    public void createProcess(MyProcess process, User common) throws ProcessException {
        if(processes.contains(process)){
            throw new ProcessException("The process is Already created inside the application");
        }
        else{
            if ()
            processes.addEnd(process);
        }
    }


}
