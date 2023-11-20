package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateProcessViewController {
    public TextField tfDescrpcion;
    TaskApp main;
    Admin admin;
    Common selectedCommon;
    public void setMain(TaskApp taskApp, Admin admin, Common common) {
        this.main = taskApp;
        this.admin = admin;
        this.selectedCommon = common;
       }


    public void onVolverClick() throws IOException {
        main.abrirPanelAdmin(admin);
    }

    public void onCrearClick() {
        if (!tfDescrpcion.getText().isBlank()){
            try{
                admin.createProcess("P"+selectedCommon.getProcesses().size()+1,tfDescrpcion.getText(),selectedCommon);
                Alerta.saltarAlertaInformacion("El proceso se agregó correctamente");
                tfDescrpcion.setText("");
            } catch (ProcessException e) {
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else {
            Alerta.saltarAlertaError("No puede dejar el nombre vacío");
        }
    }
}
