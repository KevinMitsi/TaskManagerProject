package com.example.taskmanager.model.person;

import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.listas.SimpleLinkedList;
import com.example.taskmanager.model.process.MyProcess;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Admin implements Serializable {
    private String name;
    private String id;
    private User user;
    private Map<String, Common>employees;
    private SimpleLinkedList<MyProcess>createdProcesses;

    public Admin(String name, String id, User user) {
        this.name = name;
        this.id = id;
        this.user = user;
        this.employees = new HashMap<>();
        this.createdProcesses = new SimpleLinkedList<>();
    }

    public Admin() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Common> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<String, Common> employees) {
        this.employees = employees;
    }

    public SimpleLinkedList<MyProcess> getCreatedProcesses() {
        return createdProcesses;
    }

    public void setCreatedProcesses(SimpleLinkedList<MyProcess> createdProcesses) {
        this.createdProcesses = createdProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        return Objects.equals(getId(), admin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void assignProcess(Common employee, MyProcess process) throws ProcessException {
        if(employee.getProcesses().containsValue(process)){
            throw new ProcessException("Este proceso ya está asignado dentro de este empleado");
        }
        else{
            employee.getProcesses().put(String.valueOf(employee.getProcesses().size()+1),process);
        }
    }
}
