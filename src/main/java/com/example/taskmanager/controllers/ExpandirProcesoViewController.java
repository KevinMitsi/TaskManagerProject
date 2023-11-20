package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.CompleteException;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.MyProcess;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class ExpandirProcesoViewController {
    TaskApp main;
    MyProcess process;
    Common logged;
    public Label lblDescripcion;
    public Label lblCompletado;
    public void setMain(TaskApp main, MyProcess process, Common logged){
        this.main=main;
        this.process = process;
        this.logged = logged;
        lblCompletado.setText(String.valueOf(process.isComplete()));
        lblDescripcion.setText(process.getNombre());
    }

    public void onSalirClick(ActionEvent event) throws IOException {
        main.abrirUserPanel(logged);
    }

    public void onCompletar(ActionEvent event) {
        try{
            process.complete();
            Alerta.saltarAlertaInformacion("El proceso se completó correctamente");
            main.abrirUserPanel(logged);
        } catch (CompleteException e) {
            Alerta.saltarAlertaError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
