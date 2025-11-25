package psp.comms.clienteservidorUDP;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    public static void main(String[] args) {

        int PUERTO=6789;
        int BUFFER_SIZE=1000;
        System.out.println("Soy el cliente y voy a enviar un datagrama.");

        String mensaje="Hola soy el cliente UDP";
        try (DatagramSocket socketUDP = new DatagramSocket()){ //coge puerto aleatorio que este libre y no el del servidor por que ya lo está usando para escuchar

            byte[] men = mensaje.getBytes(); //crear el buffer para el dp
            InetAddress hostServidor = InetAddress.getByName("localhost"); //con el getLocalHost vale --- la ip para el dp

            DatagramPacket peticion = new DatagramPacket(men,men.length,hostServidor,PUERTO);
            socketUDP.send(peticion);
            //el cliente manda un datagramPacket y el

            byte[] bufer = new byte[BUFFER_SIZE];
            DatagramPacket respuesta = new DatagramPacket(bufer,BUFFER_SIZE);
            socketUDP.receive(respuesta);

            System.out.println("Respuesta "+ new String(respuesta.getData()).trim()); //para limpiar y que no aparezcan los caracteres


        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
