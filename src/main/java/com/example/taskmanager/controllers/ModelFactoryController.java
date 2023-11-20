package com.example.taskmanager.controllers;

import com.example.taskmanager.exceptions.RegisterException;
import com.example.taskmanager.model.mainClass.TaskManager;
import com.example.taskmanager.model.person.Admin;
import com.example.taskmanager.model.person.Common;
import com.example.taskmanager.model.person.Rol;
import com.example.taskmanager.model.person.User;
import com.example.taskmanager.model.process.Activity;
import com.example.taskmanager.model.process.MyProcess;
import com.example.taskmanager.model.process.Task;
import com.example.taskmanager.persistencia.Persistencia;

import java.io.IOException;
import java.time.LocalDateTime;

public class ModelFactoryController {
    private TaskManager taskManager;
   private Admin admin;


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
           // guardarResourceXML();
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
        this.admin = new Admin("admin","admin");
        this.taskManager = new TaskManager();
        fillPeople();

        System.out.println("inicializando singleton" + taskManager);
    }

    private void fillPeople() throws RegisterException {
        // Create users
        Common kevin = new Common("Kevin", "1004", "kegarrapala.2003@gmail.com");
        Common ana = new Common("Ana", "1092", "anas.duquet@uqvirtual.edu.co");
        Common robinson = new Common("Robinson", "111", "ajsndkjasdn@gmail.com");

        User kevinmitsi = new User("kevinmitsi", "123", Rol.COMMON, kevin);
        User anas = new User("anas", "anas123", Rol.COMMON, ana);
        User robinsonUser = new User("robinson", "1234", Rol.COMMON, robinson);

        // Register users
        getTaskManager().registerCommon(kevinmitsi);
        getTaskManager().registerCommon(anas);
        getTaskManager().registerCommon(robinsonUser);

        // Create tasks
        Task task1 = new Task("T1", "Recoger el café de los cafetales", true, LocalDateTime.now().plusHours(2));
        Task task2 = new Task("T2", "Cargar el café en los burros", false, LocalDateTime.now().plusHours(4));

        // Create activities and add tasks
        Activity activity1 = new Activity("A1", "Preparar café", true);
        activity1.getTasksList().enqueue(task1);

        Activity activity2 = new Activity("A2", "Exportar café", false);
        activity2.getTasksList().enqueue(task2);

        // Create processes and add activities
        MyProcess process1 = new MyProcess("P1", "Vender café a los turistas");
        process1.getTaskList().addEnd(activity1);

        MyProcess process2 = new MyProcess("P2", "Llevar el café a otro país");
        process2.getTaskList().addEnd(activity2);

        // Add processes to users
        kevin.getProcesses().put(process1.getId(),process1);
        ana.getProcesses().put(process2.getId(),process2);

        // You can add more tasks, activities, and processes as needed
    }

    public TaskManager getTaskManager(){
        return taskManager;
    }

    public Admin getAdmin(){
        return admin;
    }


}
