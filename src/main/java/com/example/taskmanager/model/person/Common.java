package com.example.taskmanager.model.person;

import com.example.taskmanager.model.process.MyProcess;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Common implements Serializable {
    private String name;
    private String id;
    private String email;
    private Map<String, MyProcess> processes;

    private User user;

    public Common(String name, String id, String email, User user) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.user = user;
        this.processes = new HashMap<>();
    }

    public Common() {
        processes = new HashMap<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
