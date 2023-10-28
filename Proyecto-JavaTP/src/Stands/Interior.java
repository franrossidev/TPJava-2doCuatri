package Stands;

import Accesorios.Accesorio;

import java.util.ArrayList;

public class Interior extends Stand {

    int cantLuminarias;
    ArrayList<Long> listaAccesorios;

    public Interior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Long> _listaAccesorios, int _cantLuminarias){
        super(_IDStand, _superficie, _precioM2, _IDCliente,_listaAccesorios);
        cantLuminarias = _cantLuminarias;
    }

    @Override
    public double Valor(ArrayList<Accesorio> listaPreciosAccesorios) {
        double sumaPrecio = 0;
        // Aquí tendrás que adaptar la lógica para calcular el precio basado en los IDs de los accesorios
        // por ejemplo, si tienes una lista de precios para los IDs de los accesorios
        // debes buscar el precio de cada accesorio en esa lista y sumarlo a sumaPrecio
        return sumaPrecio + (getPrecioM2() * getSuperficie()) + cantLuminarias * 1000;
    }


    @Override
    public String mostrar(ArrayList<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nCantidad de Luminarias: " + cantLuminarias + "\n"; // Agregar información de luminarias
        return standInfo;
    }
    public ArrayList<Long> getListaAccesorios() {
        return listaAccesorios;
    }
}
