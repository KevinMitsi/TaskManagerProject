package com.example.taskmanager;

import com.example.taskmanager.controllers.*;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void abrirPanelAdmin(Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("adminPanel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AdminPanelViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin);
        stage.setTitle("Panel Admin");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirUserPanel(Common loggedCommon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("userPanel-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        UserPanelViewController controller = fxmlLoader.getController();
        controller.setMain(this, loggedCommon);
        stage.setTitle("Panel "+loggedCommon.getName());
        stage.setScene(scene);
        stage.show();
    }

    public void abriBuscarTarea() {
    }

    public void abrirExpandirTarea(Task value, Common logged) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("expandirTarea-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ExpandirTareaViewController controller = fxmlLoader.getController();
        controller.setMain(this, value,logged);
        stage.setTitle("Agregar Actividad");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirAgregarActividad(Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("agregarActividad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AgregarActividadViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin);
        stage.setTitle("Agregar Actividad");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirAgregarTarea(Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("agregarTarea-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AgregarTareaViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin);
        stage.setTitle("Agregar Tarea");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirAgregarProcess(Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("agregarProceso-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AgregarProcesoViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin);
        stage.setTitle("Agregar Proceso");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirCambiarActividades(Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("cambiar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CambiarViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin);
        stage.setTitle("Cambiar actividades");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirCreateActivity(Common value, Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("createActivity-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CreateActivityViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin, value);
        stage.setTitle("Cambiar actividades");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirCreateTask(Common value, Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("createTask-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CreateTaskViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin, value);
        stage.setTitle("Cambiar actividades");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirCreateProcess(Common value, Admin admin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("createProcess-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CreateProcessViewController controller = fxmlLoader.getController();
        controller.setMain(this, admin, value);
        stage.setTitle("Cambiar actividades");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirBuscarActividad() {
    }

    public void abrirExpandirActividad(Activity value, Common loggedCommon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("expandirActividad-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ExpandirActividadViewController controller = fxmlLoader.getController();
        controller.setMain(this, value,loggedCommon);
        stage.setTitle("Agregar Actividad");
        stage.setScene(scene);
        stage.show();
    }

    public void abrirExpandirProceso(MyProcess value, Common loggedCommon) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskApp.class.getResource("expandirProceso-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ExpandirProcesoViewController controller = fxmlLoader.getController();
        controller.setMain(this, value,loggedCommon);
        stage.setTitle("Agregar Actividad");
        stage.setScene(scene);
        stage.show();

    }
}