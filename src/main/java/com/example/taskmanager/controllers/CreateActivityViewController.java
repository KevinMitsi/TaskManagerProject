package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.ProcessException;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class CreateActivityViewController {
    public TextField tfDescrpcion;
    public ComboBox<Boolean> cbObligatoia;
    public RadioButton rbNombre;
    public ToggleGroup opcionAgregado;
    public RadioButton rbEnd;
    public RadioButton rbLast;
    public ComboBox<Activity> cbActividades;
    public ComboBox<MyProcess> cbProcesos;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    Admin admin;
    Common selectedCommon;
    Activity lastCreated;
    public void setMain(TaskApp taskApp, Admin admin, Common common) {
        this.main = taskApp;
        this.admin = admin;
        this.selectedCommon = common;
        cbProcesos.getItems().addAll(selectedCommon.getProcesses().values());
    }


    public void onVolverClick(ActionEvent event) throws IOException {
        main.abrirPanelAdmin(admin);
    }

    public void onCrearClick(ActionEvent event) {
        if(verificarCampos()){
            if (rbNombre.isSelected()){
                try {
                    Activity activity = new Activity("A"+cbProcesos.getValue().getTaskList().size()+1,tfDescrpcion.getText(),cbObligatoia.getValue());
                    admin.createActivityGivingName(cbProcesos.getValue(),cbActividades.getValue(),activity);
                    Alerta.saltarAlertaInformacion("Se ha creado la actividad correctamente");
                    lastCreated = activity;
                    limpiarCampos();
                  } catch (ProcessException e) {
                    Alerta.saltarAlertaError(e.getMessage());
                }
            }
            if (rbEnd.isSelected()){
                try{
                    Activity activity = new Activity("A"+cbProcesos.getValue().getTaskList().size()+1,tfDescrpcion.getText(),cbObligatoia.getValue());
                    admin.createActivityAtLast(cbProcesos.getValue(),activity);
                    Alerta.saltarAlertaInformacion("Se ha creado la actividad correctamente");
                    lastCreated = activity;
                    limpiarCampos();
                } catch (ProcessException e) {
                    Alerta.saltarAlertaError(e.getMessage());
                }

            }
            if (rbLast.isSelected()){
                if (lastCreated!=null){
                    try{
                        Activity activity = new Activity("A"+cbProcesos.getValue().getTaskList().size()+1,tfDescrpcion.getText(),cbObligatoia.getValue());
                        admin.createActivityUsingLast(cbProcesos.getValue(),lastCreated,activity);
                        Alerta.saltarAlertaInformacion("Se ha creado la actividad correctamente");
                        lastCreated = activity;
                        limpiarCampos();
                        } catch (ProcessException e) {
                        Alerta.saltarAlertaError(e.getMessage());
                    }
                }
                else {
                    Alerta.saltarAlertaError("No hay ninguna actividad creada anteriormente");
                    rbLast.setSelected(false);
                }
            }
        }
        else {
            Alerta.saltarAlertaError("Hay campos vacíos o mal llenados");
        }
    }

    private void limpiarCampos() {
        tfDescrpcion.setText("");
        cbActividades.setValue(null);
        cbProcesos.setValue(null);
        cbObligatoia.setValue(null);
        rbLast.setSelected(false);
        rbEnd.setSelected(false);
        rbNombre.setSelected(false);
    }

    private boolean verificarCampos() {
        if (!cbActividades.isDisable()){
            return !tfDescrpcion.getText().isBlank()&& (rbNombre.isSelected()||rbLast.isSelected()||rbEnd.isSelected()) && (cbObligatoia.getValue()!=null) && cbActividades!=null && cbProcesos.getValue()!=null;
        }
        return !tfDescrpcion.getText().isBlank()&& (rbNombre.isSelected()||rbLast.isSelected()||rbEnd.isSelected()) && (cbObligatoia.getValue()!=null) && cbProcesos.getValue()!=null;
    }

    public void rbNombreCLick(ActionEvent event) {
        cbActividades.setDisable(false);
        if (cbProcesos.getValue()!=null){
            MyProcess process = cbProcesos.getValue();
            for(Activity activity : process.getTaskList()){
                cbActividades.getItems().add(activity);
            }
        }
        else{
            Alerta.saltarAlertaError("Debe seleccionar un proceso para agregar las actividades al selector");
        }
    }

    public void rbFinalClick(ActionEvent event) {
        cbActividades.setDisable(true);
        cbActividades.getItems().clear();
    }

    public void rbLastClick(ActionEvent event) {
        cbActividades.setDisable(true);
        cbActividades.getItems().clear();
    }

    @FXML
    void initialize(){
        cbObligatoia.getItems().addAll(true,false);
    }
}
