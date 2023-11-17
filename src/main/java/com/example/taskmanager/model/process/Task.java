package com.example.taskmanager.model.process;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private String id;
    private String description;
    private boolean isMandatory;

    public Task(String id, String description, boolean isMandatory) {
        this.id = id;
        this.description = description;
        this.isMandatory = isMandatory;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getId(), task.getId()) || Objects.equals(getDescription(), task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }
}
