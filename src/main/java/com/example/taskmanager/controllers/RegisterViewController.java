package com.example.taskmanager.controllers;

import com.example.taskmanager.TaskApp;
import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.Rol;
import com.example.taskmanager.model.person.User;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewController {
    public TextField tfName;
    public TextField tfId;
    public TextField tfUser;
    public PasswordField pfPassword;
    public Hyperlink hlBack;
    public Button btnRegister;
    public TextField tfEmail;
    TaskApp main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();


    public void onBackLinkClick() throws IOException {
        main.inicializarLogin();
    }

    public void onRegisterButtonClick() {
        if (verifySpaces()) {
            try {
                verifyEmail();
                Common common = new Common(tfName.getText(), tfId.getText(), tfEmail.getText());
                singleton.getTaskManager().registerCommon(new User(tfUser.getText(), pfPassword.getText(), Rol.COMMON, common));
                Alerta.saltarAlertaInformacion("You've registered properly");
                main.inicializarLogin();
            } catch (RegisterException e) {
                Alerta.saltarAlertaError(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alerta.saltarAlertaError("Verify, there empty spaces");
        }
    }

    private void verifyEmail() throws RegisterException {
        // Assuming tfEmail is a TextField or similar component
        String email = tfEmail.getText();

        // Define the regular expression pattern for a simple email address
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailPattern);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Use the find method to check if the provided email matches the pattern
        if (!matcher.find()) {
            throw new RegisterException("Invalid email"); // Valid email address
        }
    }

    private boolean verifySpaces() {
        return !tfName.getText().isBlank() && !tfEmail.getText().isBlank() && !tfId.getText().isBlank() && !tfUser.getText().isBlank() && !pfPassword.getText().isBlank();
    }


    public void setMain(TaskApp taskApp) {
        this.main = taskApp;
    }

}
