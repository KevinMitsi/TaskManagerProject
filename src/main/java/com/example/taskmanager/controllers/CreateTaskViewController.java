package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateTaskViewController {
    public TextField tfDescrpcion;
    public ComboBox<Boolean> cbObligatoia;
    public RadioButton rbPosicion;
    public ToggleGroup opcionAgregado;
    public RadioButton rbEnd;
    public ComboBox<Task> cbTasks;
    public ComboBox<Activity> cbActividades;
    public DatePicker dpMax;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    Common selectedCommon;
    Task lastCreated;
    public void setMain(TaskApp taskApp, Admin admin, Common common) {
        this.main = taskApp;
        this.admin = admin;
        this.selectedCommon = common;
        fillBox();
    }

    private void fillBox() {
        for(MyProcess process : selectedCommon.getProcesses().values()) {
            for (Activity activity : process.getTaskList()) {
                cbActividades.getItems().add(activity);
            }
        }
    }


    public void onVolverClick(ActionEvent event) throws IOException {
        main.abrirPanelAdmin(admin);
    }

    public void rbPosicionClic(ActionEvent event) {
        cbTasks.setDisable(false);
        if (cbActividades.getValue()!=null){
            Activity activity = cbActividades.getValue();
            for(Task task : activity.getTasksList()){
                cbTasks.getItems().add(task);
            }
        }
        else {
            Alerta.saltarAlertaError("Debe seleccionar una actividad para poder llenar el selector");
        }
    }

    public void rbFinalClick(ActionEvent event) {
        cbTasks.setDisable(true);
        cbTasks.getItems().clear();
    }

    public void onCrearClick(ActionEvent event) {
        if (verificarCampos()){
            if (rbPosicion.isSelected()){
                try{
                    Task task = new Task("T"+cbActividades.getValue().getTasksList().size()+1,tfDescrpcion.getText(),cbObligatoia.getValue(), LocalDateTime.of(dpMax.getValue(), LocalTime.MIDNIGHT));
                    admin.createTaskGivingPosition(cbActividades.getValue(),cbTasks.getValue(),task);
                    Alerta.saltarAlertaInformacion("La tarea fue creada correctamente");
                    limpiarCampos();
                } catch (ProcessException e) {
                    Alerta.saltarAlertaError(e.getMessage());
                }

            }
            if (rbEnd.isSelected()){
                try{
                    Task task = new Task("T"+cbActividades.getValue().getTasksList().size()+1,tfDescrpcion.getText(),cbObligatoia.getValue(), LocalDateTime.of(dpMax.getValue(), LocalTime.MIDNIGHT));
                    Activity activity = cbActividades.getValue();
                    if (activity.getTasksList().size()==0){
                        admin.createTaskAtLast(activity,null,task);
                    }
                    else{
                        admin.createTaskAtLast(activity,activity.getTasksList().getLast(),task);
                    }
                    Alerta.saltarAlertaInformacion("La tarea fue creada correctamente");
                    limpiarCampos();
                } catch (ProcessException e) {
                    Alerta.saltarAlertaError(e.getMessage());
                }

            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos o mal llenados");
        }
    }

    private boolean verificarCampos() {
        if(!cbTasks.isDisable()){
            return !tfDescrpcion.getText().isBlank() && (rbEnd.isSelected()||rbPosicion.isSelected()) &&cbTasks.getValue()!=null&& cbActividades.getValue()!=null && cbObligatoia.getValue()!=null && dpMax.getValue()!=null;
        }
        return !tfDescrpcion.getText().isBlank() && (rbEnd.isSelected()||rbPosicion.isSelected()) && cbActividades.getValue()!=null && cbObligatoia.getValue()!=null && (dpMax.getValue()!=null&&dpMax.getValue().isAfter(LocalDate.now()));
    }private void limpiarCampos() {
        tfDescrpcion.setText("");
        cbActividades.setValue(null);
        cbTasks.setValue(null);
        cbObligatoia.setValue(null);
        rbEnd.setSelected(false);
        rbPosicion.setSelected(false);
        dpMax.setValue(null);
    }

    @FXML
    void initialize(){
        cbObligatoia.getItems().addAll(true,false);
    }

}

