package psp.comms;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;

public class HostInfo {
    public static void main(String[] args) {

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Nombre del host local: "+localhost.getHostName());
            System.out.println("Dirección IP local: "+localhost.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            InetAddress google = InetAddress.getByName("www.google.es");
            System.out.println("Nombre del host remoto: "+google.getHostName());
            System.out.println("Dirección IP remoto: "+google.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            Enumeration<NetworkInterface> interfaces =NetworkInterface.getNetworkInterfaces();
            while(interfaces.hasMoreElements()){
                NetworkInterface ni = interfaces.nextElement();

                System.out.println("Nombre de la interfaz: " + ni.getName());
                System.out.println("Interfaz activa: "+ni.isUp());
                System.out.println("Dirección MAC: "+ Arrays.toString(ni.getHardwareAddress()));

               Enumeration<InetAddress> direcciones = ni.getInetAddresses();
               while(direcciones.hasMoreElements()){
                   InetAddress ip = direcciones.nextElement();
                   System.out.println("\tDirección IP: "+ip.getHostAddress());

               }

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }


    }
}