package com.example.taskmanager.model.process;

import com.example.taskmanager.exceptions.CompleteException;

public interface Completable {
    public void complete() throws CompleteException;
}
