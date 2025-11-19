package psp.comms;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GestorDescargas {

    public void descargarArchivos(String url_descarga, String nombreFichero){
        System.out.println("Descargando: "+url_descarga);

        InputStream is;
        InputStreamReader reader;
        BufferedReader bReader;
        FileWriter escritorFichero;
        try {
            URL url =new URL(url_descarga);
             is = url.openStream(); //lee byte a byte
            reader = new InputStreamReader(is); //lee los contenidos linea a linea
            bReader = new BufferedReader(reader);
            escritorFichero = new FileWriter(nombreFichero);

            String linea; //a medida que vamos leyendo, vamos escribiendo en el fichero dado por parametro
            while((linea=bReader.readLine())!=null){
                escritorFichero.write(linea+"\n"); //poner salto de linea
            }
            escritorFichero.close();
            bReader.close();
            reader.close();
            is.close();


        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            //aqui cerrar todos los reeaders y writers

        }
    }

    public static void main(String[] args) {
        GestorDescargas gd = new GestorDescargas();
        String url = "https://www.bbc.com/robots.txt";
        gd.descargarArchivos(url,"descarga.txt");

    }
}
