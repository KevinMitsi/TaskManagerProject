package com.example.taskmanager.controllers;
import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.UserNotFoundException;
import com.example.taskmanager.listas.DoubleLinkedList;
import com.example.taskmanager.model.person.Common;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController {
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    public PasswordField passwordField;
    public TextField usernameField;

    public void registerButtonClick() throws IOException {
        main.abrirRegister();
    }

    public void loginButtonAction() throws IOException {
        if (isEmptySpaces()){
            if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin123")) {
                main.abrirPanelAdmin(singleton.getAdmin());
            }
            else {
                try {
                    Common common = singleton.getTaskManager().login(usernameField.getText(),passwordField.getText());
                    Alerta.saltarAlertaInformacion("Logging-in");
                    main.abrirUserPanel(common);
                } catch (UserNotFoundException e) {
                    Alerta.saltarAlertaError(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else{
            Alerta.saltarAlertaError("Hay campos vacíos");
        }
    }

    private boolean isEmptySpaces() {
        return !usernameField.getText().isBlank()&&!passwordField.getText().isBlank();
    }


    public void setMain(TaskApp taskApp) {
        this.main = taskApp;
        DoubleLinkedList<Integer>list= new DoubleLinkedList<>();
        list.addEnd(1);
        list.addEnd(2);
        list.addEnd(3);
        list.addEnd(4);
        list.addEnd(5);
        list.addByIndex(2+1,7);
        list.print();
    }
}
