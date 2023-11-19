package com.example.taskmanager.controllers;

import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.model.mainClass.TaskManager;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.Rol;
import com.example.taskmanager.model.person.User;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.persistencia.Persistencia;

import java.io.IOException;

public class ModelFactoryController {
    TaskManager taskManager;


    private static class SingletonHolder {
        // El constructor de Singleton puede ser llamado desde aqu� al ser protected
        private final static ModelFactoryController eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactoryController();
            } catch (IOException | RegisterException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Metodo para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() throws IOException, RegisterException {
        //Siempre se debe verificar si la raiz del recurso es null
        guardarResourceBinario();
        cargarResourceXML();
        if (taskManager == null) {
            System.out.println("es null");
            inicializarDatos();
            guardarResourceXML();
            guardarResourceBinario();
        }
        //Registrar la accion de incio de sesi�n
        Persistencia.guardaRegistroLog("Inicio de sesion del usuario:pedro", 1, "inicioSesion");
    }

    public void guardarResourceBinario() {
        Persistencia.guardarRecursoDomainBinario(taskManager);
    }


    public void cargarResourceXML() {
        taskManager = Persistencia.cargarRecursoDomainXML();
    }


    public void guardarResourceXML() {
        Persistencia.guardarRecursoDomainXML(taskManager);
    }

    private void inicializarDatos() throws RegisterException {
        this.taskManager = new TaskManager();
        fillPeople();

        System.out.println("inicializando singleton" + taskManager);
    }

    private void fillPeople() throws RegisterException {
        Common kevin = new Common("Kevin","1004","kegarrapala.2003@gmail.com");
        Common ana = new Common("Ana","1092","anas.duquet@uqvirtual.edu.co");
        Common robinson = new Common("Robinson","111","ajsndkjasdn@gmail.com");
        User kevinmitsi = new User("kevinmitsi","123", Rol.COMMON,kevin);
        User anas = new User("anas","anas123", Rol.COMMON,ana);
        User robinsonUser = new User("robinson","1234", Rol.COMMON,robinson);
        getTaskManager().registerCommon(kevinmitsi);
        getTaskManager().registerCommon(anas);
        getTaskManager().registerCommon(robinsonUser);
        MyProcess process = new MyProcess("90","Preparar taza de café");

    }

    public TaskManager getTaskManager(){
        return taskManager;
    }


}
