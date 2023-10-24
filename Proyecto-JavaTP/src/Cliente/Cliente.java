package Cliente;

public class Cliente {

    private long IDCliente;
    private String descripcion; //NOMBRE Y APELLIDO SI ES PARTICULAR O RAZON SOCIAL SI ES EMPRESA

    //METODOS

    Cliente(long _IDCliente, String _descripcion){
        IDCliente = _IDCliente; descripcion = _descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getIDCliente() {
        return IDCliente;
    }
}