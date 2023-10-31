package Stands;
import Accesorios.Accesorio;


import java.util.HashSet;

public abstract class Stand {

    private final String IDStand;
    private final int superficie;
    private final double precioM2;
    private final long IDCliente;
    private final HashSet<Long> listaAccesorios;

    //MÉTODOS

    public Stand(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios){
        IDStand = _IDStand;
        superficie = _superficie;
        precioM2 = _precioM2;
        IDCliente = _IDCliente;
        listaAccesorios = _listaAccesorios;
    }

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
    public String toString(){
        return "ID del Stand: " + IDStand + "\n" +
                "Superficie: " + superficie + "\n" +
                "Precio por metro cuadrado: " + precioM2 + "\n" +
                "ID del Cliente: " + IDCliente + "\n";
    }
    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        StringBuilder result = new StringBuilder(toString());

        if (listaAccesorios.isEmpty()) {
            result.append("Accesorios: No hay accesorios disponibles para este stand.");
        } else {
            result.append("\nAccesorios:\n");
            for (Long aux : listaAccesorios) {
                for (Accesorio acc : listaPreciosAccesorios) {
                    if(aux == acc.getIDAccesorio()){
                        result.append(acc.mostrar()).append("\n");
                        break;
                    }
                }
            }
        }
        return result.toString();
    }

    public abstract double Valor(HashSet<Accesorio> listaPreciosAccesorios);

}