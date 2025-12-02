package psp.comms.hilos.model;

import java.io.Serializable;

public class Ejemplo implements Serializable {

    private String dato;
    private int id;


    public Ejemplo(String dato,int id) {
        this.dato = dato;
        this.id=id;
    }

    public void mostrar() {
        System.out.println("Id: "+id + "\nDato: "+dato);
    }

    @Override
    public String toString() {
        return "Dato='" + dato +
                ", Id=" + id
                ;
    }
}
