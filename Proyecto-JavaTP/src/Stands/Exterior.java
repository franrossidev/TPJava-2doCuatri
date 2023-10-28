package Stands;

import Accesorios.Accesorio;

import java.util.ArrayList;

public class Exterior extends Stand {

    public Exterior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Long> _listaAccesorios) {
        super(_IDStand, _superficie, _precioM2, _IDCliente, _listaAccesorios);
    }
    public double Valor(ArrayList<Accesorio> listaPreciosAccesorios){
        return 0;
    }
/*
    @Override
    public double Valor() {
        double sumaPrecios=0;
        if(getListaAccesorios().size() >= 3) {
            for (Accesorio aux : getListaAccesorios()) {
                sumaPrecios += aux.getPrecioAlquiler() * 0.9;

            }
        }
        else{
            for (Accesorio aux : getListaAccesorios()) {
                sumaPrecios += aux.getPrecioAlquiler();
            }
        }
        return sumaPrecios + getPrecioM2() * getSuperficie();
    }*/
}