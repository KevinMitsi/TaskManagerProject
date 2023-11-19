package com.example.taskmanager;

import com.example.taskmanager.controllers.*;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TaskApp extends Application {
    private Stage stage;


    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setOnCloseRequest(event -> {
            event.consume();
            intentarCerrar();
        });
        ModelFactoryController singleton = ModelFactoryController.getInstance();
        inicializarLogin();
    }
    private void intentarCerrar() {
        if(Alerta.saltarAlertaConfirmacion("¿Seguro que quiere cerrar?","Usted está a punto de salir") == ButtonType.OK){
            System.out.println("Ha cerrado la aplicación");
            stage.close();
        }
    }

    public void inicializarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void abrirRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RegisterViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirPanelAdmin() {

    }

    public void abrirUserPanel(Common loggedCommon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("userPanel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        UserPanelViewController controller = fxmlLoader.getController();
        controller.setMain(this, loggedCommon);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public void abriBuscarTarea() {
    }

    public void abrirExpandirTarea(Task value) {
    }
}