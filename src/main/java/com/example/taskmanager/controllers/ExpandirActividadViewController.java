package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.CompleteException;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class ExpandirActividadViewController {

    public Label lblDescripcion;
    public Label lblObligatoria;
    public Label lblCompletado;

    Activity activity;
    TaskApp main;
    Common logged;

    public void setMain(TaskApp main, Activity activity, Common logged){
       this.main = main;
       this.activity = activity;
       this.logged = logged;
       lblCompletado.setText(String.valueOf(activity.isComplete()));
       lblDescripcion.setText(activity.getDescription());
       lblObligatoria.setText(String.valueOf(activity.isMandatory()));
    }

    public void onSalirClick() throws IOException {
        main.abrirUserPanel(logged);
    }

    public void onCompletar() {
        try{
           activity.complete();
           Alerta.saltarAlertaInformacion("Se completó correctamente la actividad");
           main.abrirUserPanel(logged);
        } catch (CompleteException e) {
            Alerta.saltarAlertaError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
