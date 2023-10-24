package Stands;
import Accesorios.Accesorio;
import java.util.ArrayList;

public class Interior extends Stand {

    int cantLuminarias;

    public Interior(String _IDStand, int _superficie, double _precioM2, long _IDCliente, ArrayList<Accesorio> _listaAccesorios, int _cantLuminarias){
        super(_IDStand, _superficie, _precioM2, _IDCliente, _listaAccesorios);
        cantLuminarias = _cantLuminarias;
    }

    @Override
    public double Valor() {
        return 0;//IMPLEMENTAR
    }

    public int getCantLuminarias() {
        return cantLuminarias;
    }
}