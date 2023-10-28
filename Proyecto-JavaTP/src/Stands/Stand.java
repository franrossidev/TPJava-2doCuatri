package Stands;
import Accesorios.Accesorio;

import java.util.ArrayList;
public abstract class Stand {

    private String IDStand;
    private int superficie;
    private double precioM2;
    private long IDCliente;
    private ArrayList<Long> listaAccesorios;

    //MÉTODOS

    public Stand(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Long> _listaAccesorios){
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

    public ArrayList<Long> getListaAccesorios() {
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

    public String mostrar(ArrayList<Accesorio> listaPreciosAccesorios) {
        String result = "ID del Stand: " + IDStand + "\n" +
                "Superficie: " + superficie + "\n" +
                "Precio por metro cuadrado: " + precioM2 + "\n" +
                "ID del Cliente: " + IDCliente + "\n";

        if (listaAccesorios.isEmpty()) {
            result += "Accesorios: No hay accesorios disponibles para este stand.";
        } else {
            result += "\nAccesorios:\n";
            for (int i = 0; i< listaAccesorios.size() ; i++) {
                for (int j = 0;j < listaPreciosAccesorios.size(); j++) {
                    Accesorio accesorioact = listaPreciosAccesorios.get(j);
                    if (listaAccesorios.get(i) == accesorioact.getIDAccesorio()) {
                        result += accesorioact.mostrar() + "\n";

                    }
                }
            }
        }
        return result;
    }

    public abstract double Valor(ArrayList<Accesorio> listaPreciosAccesorios);

}