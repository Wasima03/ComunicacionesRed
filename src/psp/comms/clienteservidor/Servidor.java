package psp.comms.clienteservidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor=null;
        Socket cliente = null;
        int numCliente=0;
        int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);

            System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto "+PUERTO);

            do{
                cliente= servidor.accept(); //el servidor se queda bloqueado a la espera qde que el cluiente se concente
                numCliente++;
                System.out.println("\tLlega el cliente "+numCliente);

                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                dos.writeUTF("Usted es mi cliente "+ numCliente);

                cliente.close();
                System.out.println("\tSe ha cerrado la conexión con el cliente "+numCliente);

            }while(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                servidor.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
