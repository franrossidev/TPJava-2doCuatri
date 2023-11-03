package Stands;

import Accesorios.Accesorio;

import java.util.HashSet;

public class Exterior extends Stand {

    public Exterior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios) {
        super(_IDStand, _superficie, _precioM2, _IDCliente, _listaAccesorios);
    }

    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nEl valor del stand es: " + Valor(listaPreciosAccesorios) + "\n";
        return standInfo;
    }

    @Override
    public double Valor(HashSet<Accesorio> listaPreciosAccesorios) {
        double sumaPrecio=0;
        if(getListaAccesorios().size() >= 3) {
            for (Accesorio accesorio : listaPreciosAccesorios) {
                sumaPrecio += accesorio.getPrecioAlquiler() * 0.9;

            }
        }
        else{
            for (Accesorio accesorio : listaPreciosAccesorios) {
                sumaPrecio += accesorio.getPrecioAlquiler();
            }
        }
        return sumaPrecio + getPrecioM2() * getSuperficie();
    }
}