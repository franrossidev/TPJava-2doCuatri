package Stands;

import Accesorios.Accesorio;

import java.util.HashSet;

public class Interior extends Stand {

    int cantLuminarias;

    public Interior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios, int _cantLuminarias){
        super(_IDStand, _superficie, _precioM2, _IDCliente,_listaAccesorios);
        cantLuminarias = _cantLuminarias;
    }

    @Override
    public double Valor(HashSet<Accesorio> listaPreciosAccesorios) {
        double sumaPrecio = 0;
        for(long aux : getListaAccesorios()) {
            for (Accesorio acc : listaPreciosAccesorios) {
                if (aux == acc.getIDAccesorio()) {
                    sumaPrecio += acc.getPrecioAlquiler();
                    break;
                }
            }
        }
        return sumaPrecio + (getPrecioM2() * getSuperficie()) + (cantLuminarias * 1000);
    }


    @Override
    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nCantidad de Luminarias: " + cantLuminarias + "\n"; // Agregar información de luminarias
        return standInfo;
    }
}
