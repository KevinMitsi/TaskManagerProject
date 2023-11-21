package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class CambiarViewController {
    public ComboBox<Activity> cbActividad1;
    public ComboBox<Activity> cbActividad2;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    Common commonLogged;
    public void setMain(TaskApp taskApp, Admin admin, Common common) {
        this.main = taskApp;
        this.admin = admin;
        this.commonLogged = common;
        for(MyProcess process : commonLogged.getProcesses().values()){
            for(Activity activity : process.getTaskList()){
                cbActividad1.getItems().add(activity);
                cbActividad2.getItems().add(activity);
            }
        }
    }

    public void onSalir() throws IOException {
        main.abrirPanelAdmin(admin);
    }

    public void onCambiar() {
        if (verificarCampos()){
            try{
                singleton.getTaskManager().changeActivities(cbActividad1.getValue(),cbActividad2.getValue());
                Alerta.saltarAlertaInformacion("Las actividades han sido cambiadas correctamente");
                main.abrirPanelAdmin(admin);
            }catch (Exception e){
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else{
            Alerta.saltarAlertaError("No ha seleccionado una actividad o ha elegido la misma");
        }
    }

    private boolean verificarCampos(){
        return cbActividad1.getValue()!=null && cbActividad2!=null && (!cbActividad1.getValue().equals(cbActividad2.getValue()));
    }
}
