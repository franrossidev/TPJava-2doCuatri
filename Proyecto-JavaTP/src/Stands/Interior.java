package Stands;

import Accesorios.Accesorio;

import java.util.HashSet;

/**
 * Clase interior hereda de clase base stand y representa a los stands que son de interiores.
 */
public class Interior extends Stand {

    int cantLuminarias;

    /**
     * Crea un stand de tipo interior con los siguientes parametros:
     * @param _IDStand representa la identificacion univoca de cada stand.
     * @param _superficie es la superficie que ocupa el stand en metros cuadrados.
     * @param _precioM2 es el precio por metro cuadrado del stand.
     * @param _IDCliente representa la identificacion del cliente que contrato el stand.
     * @param _listaAccesorios listaAccesorios es la lista de los ID's de los accesorios que el cliente contrato para el stand.
     * @param _cantLuminarias es la cantidad de luminarias requeridas para iluminar el stand.
     */
    public Interior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, HashSet<Long> _listaAccesorios, int _cantLuminarias){
        super(_IDStand, _superficie, _precioM2, _IDCliente,_listaAccesorios);
        cantLuminarias = _cantLuminarias;
    }

    /**
     * Sobreescribe Valor() de clase base stand.
     * @param listaPreciosAccesorios es la lista con todos los accesorios disponibles para ser contratados. contiene su ID, descripcion y precio.
     * @return devuelve el valor para un stand de interior, segun sus atributos.
     */
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


    /**
     * Sobreescribe el metodo abstracto mostrar() de la clase base stand.
     * @param listaPreciosAccesorios es la lista de los accesorios que los stand pueden contratar.
     * @return devuelve un objeto de tipo String con todos los atributos de los stand de interiores, y tambien su valor.
     */
    @Override
    public String mostrar(HashSet<Accesorio> listaPreciosAccesorios) {
        String standInfo = super.mostrar(listaPreciosAccesorios); // Obtener la información básica del stand
        standInfo += "\nEl valor del stand es: " + Valor(listaPreciosAccesorios) + "\n"
        + "\nCantidad de Luminarias: " + cantLuminarias + "\n"; // Agregar información de luminarias
        return standInfo;
    }
}
