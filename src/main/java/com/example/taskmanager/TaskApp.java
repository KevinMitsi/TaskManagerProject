package com.example.taskmanager;

import com.example.taskmanager.controllers.Alerta;
import com.example.taskmanager.controllers.LoginViewController;
import com.example.taskmanager.controllers.RegisterViewController;
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
}