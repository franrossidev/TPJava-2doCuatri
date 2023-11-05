package Cliente;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Clase cliente representa al cliente que contrata stands.
 */
public class Cliente {

    private final long IDCliente;
    private final String descripcion; //NOMBRE Y APELLIDO SI ES PARTICULAR O RAZON SOCIAL SI ES EMPRESA

    //METODOS

    /**
     * Crea un cliente proporcionando los siguientes parametros:
     * @param _IDCliente identificador univoco del cliente.
     * @param _descripcion nombre y apellido si es persona, razon social si es una empresa.
     */
    public Cliente(long _IDCliente, String _descripcion){
        IDCliente = _IDCliente; descripcion = _descripcion;
    }

    /**
     * @return devuelve la descripcion del cliente.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return devuelve el ID del cliente.
     */
    public long getIDCliente() {
        return IDCliente;
    }

    /**
     * Metodo que realiza la carga de datos de clientes.
     * @param xmlFilePath Es el archivo XML del cual se extraen los datos de los clientes.
     * @return devuelve una lista de tipo ArrayList con las datos ya cargados.
     */
    public static ArrayList<Cliente> cargaXML(String xmlFilePath) {
        try {
            // Crear una fábrica de constructores de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(new File(xmlFilePath));

            // Obtener la lista de nodos de cliente
            NodeList nodeList = document.getElementsByTagName("cliente");

            // Crear una lista de clientes
            ArrayList<Cliente> clientes = new ArrayList<>();

            // Recorrer cada nodo de cliente y crear un objeto Cliente
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    long IDCliente = Long.parseLong(element.getElementsByTagName("idCliente").item(0).getTextContent());
                    String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();
                    Cliente cliente = new Cliente(IDCliente, descripcion);
                    clientes.add(cliente);
                }
            }
            return clientes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}