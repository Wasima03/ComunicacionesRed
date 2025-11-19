package psp.comms.clienteservidor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public static void main(String[] args) {
        InetAddress direccion;
        Socket servidor = null;
        int PUERTO = 5000;

        System.out.println("Soy el cliente e intento conectarme ");

        try {
            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion,PUERTO);

            System.out.println("Conexión realizada con éxito");

        } catch (UnknownHostException e) {
           e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if(servidor!=null) {
                    servidor.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
