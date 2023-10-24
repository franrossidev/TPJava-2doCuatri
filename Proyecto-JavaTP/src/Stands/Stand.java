package Stands;
import Accesorios.Accesorio;

import java.util.ArrayList;
public abstract class Stand {

    private String IDStand;
    private int superficie;
    private double precioM2;
    private long IDCliente;
    private ArrayList<Accesorio> listaAccesorios;

    //METODOS

    public Stand(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Accesorio> _listaAccesorios){
        IDStand = _IDStand; superficie = _superficie; precioM2 = _precioM2; IDCliente = _IDCliente;
        listaAccesorios = _listaAccesorios;
    };

    //DESARROLLAR EL TOSTRING

    public String getIDStand() {
        return IDStand;
    }

    public ArrayList<Accesorio> getListaAccesorios() {
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

    public abstract double Valor();

}