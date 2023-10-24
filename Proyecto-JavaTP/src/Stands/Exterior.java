package Stands;

import Accesorios.Accesorio;

import java.util.ArrayList;

public class Exterior extends Stand {

    public Exterior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Accesorio> _listaAccesorios) {
        super(_IDStand, _superficie, _precioM2, _IDCliente, _listaAccesorios);
    }

    @Override
    public double Valor() {
        return 0;//IMPLEMENTAR
    }
}