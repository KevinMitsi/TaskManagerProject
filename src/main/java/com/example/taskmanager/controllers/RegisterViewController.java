package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;

public class RegisterViewController {
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    public void setMain(TaskApp taskApp) {
        this.main=taskApp;
    }
}
