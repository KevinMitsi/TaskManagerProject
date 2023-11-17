package com.example.taskmanager.persistencia;

import com.example.taskmanager.model.principal.TaskManager;

public class Persistencia {
    public static final String RUTA_ARCHIVO_LOG = "src/main/java/com/example/taskmanager/archivo/Log.log";
    public static final String RUTA_ARCHIVO_MODELO_TALLER_BINARIO = "src/main/java/com/example/taskmanager/archivo/model.dat";
    public static final String RUTA_ARCHIVO_MODELO_TALLER_XML = "src/main/java/com/example/taskmanager/archivo/model.xml";


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {

        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }


    //------------------------------------SERIALIZACI�N  y XML


    public static TaskManager cargarRecursoHostalBinario() {

        TaskManager plataforma = null;

        try {
            plataforma = (TaskManager) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TALLER_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return plataforma;
    }

    public static void guardarRecursoDomainBinario(TaskManager plataforma) {

        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TALLER_BINARIO, plataforma);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static TaskManager cargarRecursoDomainXML() {

        TaskManager plataforma = null;

        try {
            plataforma = (TaskManager) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TALLER_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return plataforma;
    }



    public static void guardarRecursoDomainXML(TaskManager plataforma) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TALLER_XML, plataforma);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}