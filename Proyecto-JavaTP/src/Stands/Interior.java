package Stands;

import Accesorios.Accesorio;

import java.util.ArrayList;

public class Interior extends Stand {

    int cantLuminarias;

    public Interior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Long> _listaAccesorios, int _cantLuminarias){
        super(_IDStand, _superficie, _precioM2, _IDCliente,_listaAccesorios);
        cantLuminarias = _cantLuminarias;
    }

    @Override
    public double Valor(ArrayList<Accesorio> listaPreciosAccesorios) {
        double sumaPrecio = 0;
        int i=0;
        for (Accesorio accesorio : listaPreciosAccesorios) {

            if(accesorio.getIDAccesorio()==getListaAccesorios().get(i++)){
                sumaPrecio+=accesorio.getPrecioAlquiler();
            }
        }

        return sumaPrecio + (getPrecioM2() * getSuperficie()) + cantLuminarias * 1000;
    }


    @Override
    public String mostrar(ArrayList<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nCantidad de Luminarias: " + cantLuminarias + "\n"; // Agregar información de luminarias
        return standInfo;
    }
}
