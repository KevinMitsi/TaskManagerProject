package com.example.taskmanager.persistencia;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil {
    static String fechaSistema = "";


    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
    {
        String log = "";
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler = null;

        cargarFechaSistema();

        try {

            fileHandler = new FileHandler(rutaArchivo,true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            switch (nivel) {
                case 1 -> LOGGER.log(Level.INFO, accion + "," + mensajeLog + "," + fechaSistema);
                case 2 -> LOGGER.log(Level.WARNING, accion + "," + mensajeLog + "," + fechaSistema);
                case 3 -> LOGGER.log(Level.SEVERE, accion + "," + mensajeLog + "," + fechaSistema);
                default -> {
                }
            }

        } catch (SecurityException e) {

            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }
        finally {

            assert fileHandler != null;
            fileHandler.close();
        }

    }

    private static void cargarFechaSistema() {

        String diaN = "";
        String mesN = "";
        String anioN = "";

        Calendar cal1 = Calendar.getInstance();


        int  dia = cal1.get(Calendar.DATE);
        int mes = cal1.get(Calendar.MONTH)+1;
        int anio = cal1.get(Calendar.YEAR);
        int hora = cal1.get(Calendar.HOUR);
        int minuto = cal1.get(Calendar.MINUTE);


        if(dia < 10){
            diaN+="0"+dia;
        }
        else{
            diaN+=""+dia;
        }
        if(mes < 10){
            mesN+="0"+mes;
        }
        else{
            mesN+=""+mes;
        }

        //		fecha_Actual+= a�o+"-"+mesN+"-"+ diaN;
        //		fechaSistema = a�o+"-"+mesN+"-"+diaN+"-"+hora+"-"+minuto;
        fechaSistema = anio+"-"+mesN+"-"+diaN;
        //		horaFechaSistema = hora+"-"+minuto;
    }




    //------------------------------------SERIALIZACI�N  y XML
    /**
     * Escribe en el fichero que se le pasa el objeto que se le envia
     *
     * @param rutaArchivo
     *            path del fichero que se quiere escribir
     * @throws IOException
     */

    @SuppressWarnings("unchecked")
    public static Object cargarRecursoSerializado(String rutaArchivo)throws Exception
    {
        Object aux = null;
//		Empresa empresa = null;
        ObjectInputStream ois = null;
        try {
            // Se crea un ObjectInputStream
            ois = new ObjectInputStream(new FileInputStream(rutaArchivo));

            aux = ois.readObject();

        } catch (Exception e2) {
            throw e2;
        } finally {
            if (ois != null)
                ois.close();
        }
        return aux;
    }


    public static void salvarRecursoSerializado(String rutaArchivo, Object object)	throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oos.writeObject(object);
        } catch (Exception e) {
            throw e;
        } finally {
            if (oos != null)
                oos.close();
        }
    }




    public static Object cargarRecursoSerializadoXML(String rutaArchivo) throws IOException {

        XMLDecoder decodificadorXML;
        Object objetoXML;

        decodificadorXML = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXML = decodificadorXML.readObject();
        decodificadorXML.close();
        return objetoXML;

    }

    public static void salvarRecursoSerializadoXML(String rutaArchivo, Object objeto) throws IOException {

        XMLEncoder codificadorXML;

        codificadorXML = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXML.writeObject(objeto);
        codificadorXML.close();

    }




}