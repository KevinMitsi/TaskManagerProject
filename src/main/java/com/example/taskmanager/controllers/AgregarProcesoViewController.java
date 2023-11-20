package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class AgregarProcesoViewController {
    public ComboBox<Common> cbOpciones;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    public void setMain(TaskApp taskApp, Admin admin) {
        this.main = taskApp;
        this.admin = admin;
    }

    public void onVolverClick() throws IOException {
        main.abrirPanelAdmin(admin);
    }

    public void onAgregar() throws IOException {
        if(cbOpciones.getValue()!=null){
            main.abrirCreateProcess(cbOpciones.getValue(),admin);
        }
        else{
            Alerta.saltarAlertaError("Debe seleccionar un empleado");
        }
    }
    @FXML
    void initialize(){
        for(User user: singleton.getTaskManager().getUserMap().values()){
            cbOpciones.getItems().add(user.getAssociated());
        }
    }
}
