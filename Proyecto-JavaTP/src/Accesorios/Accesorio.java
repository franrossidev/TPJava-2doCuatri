package Accesorios;

public class Accesorio {

    private long IDAccesorio;
    private String descripcion;
    private double precioAlquiler;

    //METODOS

    Accesorio(long _IDAccesorio, String _descripcion, double _precioAlquiler){
        IDAccesorio = _IDAccesorio; descripcion = _descripcion; precioAlquiler = _precioAlquiler;
    }

    public long getIDAccesorio() {
        return IDAccesorio;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public String getDescripcion() {
        return descripcion;
    }
}