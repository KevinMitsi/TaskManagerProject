package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Task;
import javafx.scene.control.Label;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExpandirTareaViewController {
    public Label lblDescripcion;
    public Label lblObligatoria;
    public Label lblTiempo;
    public Label lblCompletado;
    TaskApp main;
     Task task;
    Common logged;
    public void setMain(TaskApp taskApp, Task value, Common logged) {
        this.main = taskApp;
        task = value;
        this.logged = logged;
        lblCompletado.setText(String.valueOf(task.isComplete()));
        lblDescripcion.setText(task.getDescription());
        lblTiempo.setText(String.valueOf(task.getMaxDay()));
        lblObligatoria.setText(String.valueOf(task.isMandatory()));
    }

    public void onSalirClick() throws IOException {
        main.abrirUserPanel(logged);
    }

    public void onCompletar() throws IOException {
        if (LocalDateTime.now().isAfter(task.getMaxDay())){
            Alerta.saltarAlertaError("No se puede completar esta tarea porque está vencida");
        }
        else{
            task.complete();
            Alerta.saltarAlertaInformacion("La tarea se completó");
            main.abrirUserPanel(logged);
        }
    }
}
