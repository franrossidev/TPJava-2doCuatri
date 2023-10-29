package Stands;
import Accesorios.Accesorio;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Stand {

    private String IDStand;
    private int superficie;
    private double precioM2;
    private long IDCliente;
    private HashSet<Long> listaAccesorios;

    //MÉTODOS

    public Stand(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios){
        IDStand = _IDStand;
        superficie = _superficie;
        precioM2 = _precioM2;
        IDCliente = _IDCliente;
        listaAccesorios = _listaAccesorios;
    };

    //DESARROLLAR EL TOSTRING

    public String getIDStand() {
        return IDStand;
    }

    public HashSet<Long> getListaAccesorios() {
        return listaAccesorios;
    }

    public double getPrecioM2() {
        return precioM2;
    }

    public int getSuperficie() {
        return superficie;
    }

    public long getIDCliente() {
        return IDCliente;
    }

    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        String result = "ID del Stand: " + IDStand + "\n" +
                "Superficie: " + superficie + "\n" +
                "Precio por metro cuadrado: " + precioM2 + "\n" +
                "ID del Cliente: " + IDCliente + "\n";

        if (listaAccesorios.isEmpty()) {
            result += "Accesorios: No hay accesorios disponibles para este stand.";
        } else {
            result += "\nAccesorios:\n";
            for (Long aux : listaAccesorios) {
                for (Accesorio acc : listaPreciosAccesorios) {
                    if(aux == acc.getIDAccesorio()){
                        result += acc.mostrar() + "\n";
                        break;///tendria que averiguar si este break rompe solo el if, el for o los 2 for
                    }
                }
            }
        }
        return result;
    }

    public abstract double Valor(HashSet<Accesorio> listaPreciosAccesorios);

}