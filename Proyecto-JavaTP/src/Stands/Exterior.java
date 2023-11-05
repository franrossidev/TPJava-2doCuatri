package Stands;

import Accesorios.Accesorio;

import java.util.HashSet;

/**
 * Clase exterior hereda de stand y representa los stands de exteriores.
 */
public class Exterior extends Stand {

    /**
     * Crea un stand de tipo exterior con los siguientes parametros:
     * @param _IDStand representa la identificacion univoca de cada stand.
     * @param _superficie es la superficie que ocupa el stand en metros cuadrados.
     * @param _precioM2 precioM2 es el precio por metro cuadrado del stand.
     * @param _IDCliente IDCliente representa la identificacion del cliente que contrato el stand.
     * @param _listaAccesorios listaAccesorios es la lista de los ID's de los accesorios que el cliente contrato para el stand.
     */
    public Exterior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios) {
        super(_IDStand, _superficie, _precioM2, _IDCliente, _listaAccesorios);
    }

    /**
     * Sobreescribe el metodo mostrar() de la clase base stand.
     * @param listaPreciosAccesorios es la lista de los accesorios que los stand pueden contratar.
     * @return devuelve un objetoc de tipo string con todos los atributos de los stand de exteriores, y tambien su valor.
     */
    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nEl valor del stand es: " + Valor(listaPreciosAccesorios) + "\n";
        return standInfo;
    }

    /**
     * Sobreescribe el metodo abstracto Valor de la clase base stand
     * @param listaPreciosAccesorios es la lista con todos los accesorios disponibles para ser contratados. contiene su ID, descripcion y precio.
     * @return devuelve el valor para un stand de tipo exterior, segun sus atributos.
     */
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