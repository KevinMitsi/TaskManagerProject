module com.example.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens com.example.taskmanager to javafx.fxml;
    exports com.example.taskmanager;
    opens com.example.taskmanager.controllers to javafx.fxml;
    exports com.example.taskmanager.controllers;
    exports com.example.taskmanager.model.mainClass;
    exports com.example.taskmanager.model.person;
    exports com.example.taskmanager.model.process;
    exports com.example.taskmanager.exceptions;
    exports com.example.taskmanager.listas;



}