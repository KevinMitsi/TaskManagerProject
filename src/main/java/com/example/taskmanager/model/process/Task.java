package com.example.taskmanager.model.process;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Serializable, Completable{
    private String id;
    private String description;
    private boolean isMandatory;
    private LocalDateTime maxTime;
    private boolean isComplete;

    public Task(String id, String description, boolean isMandatory, LocalDateTime maxTime) {
        this.id = id;
        this.description = description;
        this.isMandatory = isMandatory;
        this.maxTime = maxTime;
        this.isComplete = false;
    }

    public Task() {
    }

    public String getId() {
        return id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
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

    public LocalDateTime getMaxDay() {
        return maxTime;
    }

    public void setMaxDay(LocalDateTime maxDay) {
        this.maxTime = maxDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription());
    }

    @Override
    public void complete() {
        setComplete(true);
    }

    @Override
    public String toString() {
        return description;
    }

    public boolean isWithinDuration() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return !currentDateTime.isAfter(maxTime);
    }

    public boolean isDueSoon() {
        LocalDateTime now = LocalDateTime.now();
        return !isComplete() && getMaxDay().isBefore(now.plusDays(1)); // Considera "próximo día" como un límite para estar "a punto de vencer"
    }

}
