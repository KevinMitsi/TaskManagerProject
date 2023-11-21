package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class SearchActivityViewController {
    public ComboBox<Activity> cbOpciones;
    public TextField tfKeyWord;
    Common logged;
    TaskApp main;
    public void setMain(TaskApp main, Common common){
        this.main = main;
        this.logged=common;
    }
    public void onClickOpciones() throws IOException {
        if (cbOpciones.getValue()!=null){
            main.abrirExpandirActividad(cbOpciones.getValue(),logged);
        }
    }

    public void onVolverClick() throws IOException {
        main.abrirUserPanel(logged);
    }

    public void onBuscar() {
        if (!tfKeyWord.getText().isBlank()){
            try{
                ArrayList<Activity> foundActivities = logged.searchActivity(tfKeyWord.getText());
                cbOpciones.setDisable(false);
                cbOpciones.getItems().setAll(foundActivities);
            }catch (Exception e){
                Alerta.saltarAlertaError(e.getMessage());
            }
        }
        else {
            Alerta.saltarAlertaError("Ingrese una palabra");
        }
    }
}
