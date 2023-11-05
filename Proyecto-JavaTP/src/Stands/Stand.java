package Stands;
import Accesorios.Accesorio;


import java.util.HashSet;


/**
 * @author Menna Bruno, Alvarez Federico, Rossi Franco
 * Stand representa la Clase base de la jerarquia de los stands. En la implementacion hay 2 tipos de ellos, interiores y exteriores.
 */
public abstract class Stand {

    /**
     * Atributos no se documentan porque son privados.
     */
    private final String IDStand;
    private final int superficie;
    private final double precioM2;
    private final long IDCliente;
    private final HashSet<Long> listaAccesorios;

    //MÉTODOS

    /**
     * Constructor de clase base stand, recibe los siguientes parametros:
     * @param _IDStand representa la identificacion univoca de cada stand.
     * @param _superficie es la superficie que ocupa el stand en metros cuadrados.
     * @param _precioM2 precioM2 es el precio por metro cuadrado del stand.
     * @param _IDCliente IDCliente representa la identificacion del cliente que contrato el stand.
     * @param _listaAccesorios listaAccesorios es la lista de los ID's de los accesorios que el cliente contrato para el stand.
     */
    public Stand(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios){
        IDStand = _IDStand;
        superficie = _superficie;
        precioM2 = _precioM2;
        IDCliente = _IDCliente;
        listaAccesorios = _listaAccesorios;
    }

    /**
     * @return devuelve el id del stand.
     */
    public String getIDStand() {
        return IDStand;
    }

    /**
     * @return devuelve la lista de los accesorios.
     */
    public HashSet<Long> getListaAccesorios() {
        return listaAccesorios;
    }

    /**
     * @return devuelve el precio por metro cuadrado del stand.
     */
    public double getPrecioM2() {
        return precioM2;
    }

    /**
     * @return devuelve la superficie del stand en metros cuadrados.
     */
    public int getSuperficie() {
        return superficie;
    }

    /**
     * @return devuelve el ID del cliente que contrato el stand.
     */
    public long getIDCliente() {
        return IDCliente;
    }

    /**
     * sobreescribe el metodo toString() de la clase Object
     * @return devuelve un objeto de tipo String con todos los atributos menos la lista de accesorios.
     */
    public String toString(){
        return "ID del Stand: " + IDStand + "\n" +
                "Superficie: " + superficie + "\n" +
                "Precio por metro cuadrado: " + precioM2 + "\n" +
                "ID del Cliente: " + IDCliente + "\n";
    }

    /**
     * @param listaPreciosAccesorios es la lista de los accesorios que los stand pueden contratar.
     * @return devuelve un objeto de tipo String con todos los atributos del stand, incluidos los accesorios contratados, con su ID, descripcion y precio.
     */
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

    /**
     * @param listaPreciosAccesorios es la lista con todos los accesorios disponibles para ser contratados. contiene su ID, descripcion y precio.
     * @return devuelve el valor total del stand, dependiendo si este es exterior o interior.
     */
    public abstract double Valor(HashSet<Accesorio> listaPreciosAccesorios);

}