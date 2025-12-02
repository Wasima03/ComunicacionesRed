package psp.comms.hilos.server;

import psp.comms.hilos.model.Ejemplo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GestionClientes extends Thread{

    private int numClientes;
    private Socket cliente;

    public GestionClientes(Socket cliente, int numClinetes){
        this.numClientes=numClinetes;
        this.cliente=cliente;
    }

    //lee un dato de tipo ejemplo y crea otro y lo envia
    @Override
    public void run(){

        ObjectInputStream bufferEntrada = null;
        ObjectOutputStream bufferSalida=null;

        System.out.println("Hilo de atención al cliente "+numClientes);
        try {
            bufferEntrada=new ObjectInputStream(cliente.getInputStream()); //flujo por el que me va a llegar los datos
            Ejemplo datosEntrada = (Ejemplo) bufferEntrada.readObject();
            datosEntrada.mostrar();

            Ejemplo datoSalida = new Ejemplo("Objeto del servidor",numClientes);
            bufferSalida=new ObjectOutputStream(cliente.getOutputStream()); //flujo por el que me pueden pasar datos
            bufferSalida.writeObject(datoSalida);
            bufferSalida.flush(); //para que se envie ya y no se quede bloqueado esperando a mas datos
            System.out.println("Enviado al cliente "+numClientes + " el objeto "+datoSalida.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                bufferEntrada.close();
                bufferSalida.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }
}
