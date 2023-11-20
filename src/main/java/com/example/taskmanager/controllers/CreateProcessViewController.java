package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import javafx.event.ActionEvent;

import java.io.IOException;

public class CreateProcessViewController {
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    Common selectedCommon;
    public void setMain(TaskApp taskApp, Admin admin, Common common) {
        this.main = taskApp;
        this.admin = admin;
        this.selectedCommon = common;
       }


    public void onVolverClick(ActionEvent event) throws IOException {
        main.abrirPanelAdmin(admin);
    }
}
