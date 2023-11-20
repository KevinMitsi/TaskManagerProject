package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Admin;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminPanelViewController {
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    public void setMain(TaskApp taskApp, Admin admin) {
        this.main = taskApp;
        this.admin = admin;
    }

    public void onVolverLnkClick() throws IOException {
        main.inicializarLogin();
    }

    public void onAddActivityClick() throws IOException {
        main.abrirAgregarActividad(admin);
    }

    public void onAddTaskClick() throws IOException {
        main.abrirAgregarTarea(admin);
    }

    public void onAddProcess() throws IOException {
        main.abrirAgregarProcess(admin);
    }

    public void onChageOrderClik() throws IOException {
        main.abrirCambiarActividades(admin);
    }
}
