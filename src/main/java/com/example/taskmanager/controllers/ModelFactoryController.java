package com.example.taskmanager.controllers;

import com.example.taskmanager.model.principal.TaskManager;
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Metodo para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() throws IOException {
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

    private void inicializarDatos() {
        taskManager = new TaskManager();
        System.out.println("inicializando singleton" + taskManager);
    }
    public TaskManager getTaskManager(){
        return this.taskManager;
    }


}
