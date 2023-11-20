package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Admin;

public class CambiarViewController {
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    public void setMain(TaskApp taskApp, Admin admin) {
        this.main = taskApp;
        this.admin = admin;
    }

}
