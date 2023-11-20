package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.model.mainClass.TaskManager;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.Completable;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserPanelViewController {
    public TreeView<Completable> treeProcess;
    public Label lblBienvenida;
    public RadioMenuItem rmiHere;
    public ToggleGroup notification;
    public RadioMenuItem rmiEmail;
    public MenuItem mIExit;
    public MenuItem miSearch;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    ArrayList<Task>tasksForNotification;
    Common loggedCommon;
    public void setMain(TaskApp taskApp, Common loggedCommon) {
        this.main =taskApp;
        this.loggedCommon = loggedCommon;
        lblBienvenida.setText("Bienvenido, " + loggedCommon.getName());
        fillTreeView();
    }

    public void onRequestMenu() {
        TreeItem<Completable>selected = treeProcess.getSelectionModel().getSelectedItem();
        if (selected!=null){
            if (selected.getValue() instanceof Task){
                main.abrirExpandirTarea((Task)selected.getValue());
            }
        }
    }

    public void onExitMenuItem() throws IOException {
        main.inicializarLogin();
    }

    public void onSearchMenuItem() {
        main.abriBuscarTarea();
    }


    private void fillTreeView() {
        TreeItem<Completable> root = new TreeItem<>(new MyProcess("main","MainComletables"));
        treeProcess.setRoot(root);
        for (MyProcess process : loggedCommon.getProcesses().values()) {
            TreeItem<Completable> processItem = createCompletableTreeItem(process);

            for (Activity activity : process.getTaskList()) {
                TreeItem<Completable> activityItem = createCompletableTreeItem(activity);

                for (Task task : activity.getTasksList()) {
                    TreeItem<Completable> taskItem = createCompletableTreeItem(task);
                    activityItem.getChildren().add(taskItem);
                    if (task.isDueSoon()){
                        tasksForNotification.add(task);
                    }
                }
                processItem.getChildren().add(activityItem);
            }
            treeProcess.getRoot().getChildren().add(processItem);
        }
    }

    private TreeItem<Completable> createCompletableTreeItem(Completable completable) {
        TreeItem<Completable> treeItem = new TreeItem<>(completable);

        // Simular deshabilitar el nodo si está completado
        if (completable instanceof Task && ((Task) completable).isComplete()) {
            treeItem.setGraphic(createDisabledGraphic(completable.toString()));
        } else if (completable instanceof Activity && ((Activity) completable).isComplete()) {
            treeItem.setGraphic(createDisabledGraphic(completable.toString()));
        } else if (completable instanceof MyProcess && ((MyProcess) completable).isComplete()) {
            treeItem.setGraphic(createDisabledGraphic(completable.toString()));
        }
        return treeItem;
    }



    private Node createDisabledGraphic(String text) {
        Text textNode = new Text(text);
        textNode.setFill(Color.GREEN);
        textNode.setStrikethrough(true);
        return textNode;
    }

    public void onNotificacionesCLick(ActionEvent event) {
        if (!rmiEmail.isSelected()&&!rmiHere.isSelected()){
            Alerta.saltarAlertaError("Debe seleccionar una preferencia de notificación en el menú superior izquierdo");
        }
        else {
            if (rmiEmail.isSelected()){

            }
            if (rmiHere.isSelected()){
                for(Task task : tasksForNotification){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Tarea a punto de vencer");
                    alert.setHeaderText("La tarea '" + task.getDescription() + "' está a punto de vencer.");
                    alert.setContentText("La fecha límite es " + task.getMaxDay());
                    alert.showAndWait();
                }
            }
        }
    }
}
