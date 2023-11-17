package com.example.taskmanager.controllers;
import com.example.taskmanager.TaskApp;
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

    public void loginButtonAction() {
    }

    public void setMain(TaskApp taskApp) {
        this.main = taskApp;
    }
}
