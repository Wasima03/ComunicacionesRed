package psp.comms.hilos.client;

import psp.comms.hilos.model.Ejemplo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        InetAddress direccion;
        Socket servidor = null;
        int PUERTO = 5000; //tiene que ser el mismo que el del servidor

        System.out.println("Soy el cliente e intento conectarme ");

        ObjectOutputStream bufferSalida = null;
        ObjectInputStream bufferEntrada=null;

        try {
            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion,PUERTO);
            System.out.println("Conexión realizada con éxito");


            //hay que crear un objeto y enviarlo al servidor antes de leer lo que nos manda el
            //porque el servidor está a la espera de que el cliente le mande algo


            bufferSalida = new ObjectOutputStream(servidor.getOutputStream());
            Ejemplo datosSalida=new Ejemplo("Objeto enviado por el cliente",1);
            bufferSalida.writeObject(datosSalida);
            bufferSalida.flush();
            System.out.println("Enviado al servidor el objeto "+datosSalida.toString());

            Thread.sleep(10000);


             bufferEntrada =new ObjectInputStream(servidor.getInputStream());
             Ejemplo datosEntrada= (Ejemplo) bufferEntrada.readObject();
             datosEntrada.mostrar();




            //no se pueden crear dos flujos sobre un inputStream
            //DataInputStream datos = new DataInputStream(servidor.getInputStream());
            //System.out.println(datos.readUTF());



        } catch (UnknownHostException e) {
           e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferSalida.close();
                bufferEntrada.close();
                if(servidor!=null) {
                    servidor.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
