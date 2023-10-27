package Accesorios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Accesorio {

    private long IDAccesorio;
    private String descripcion;
    private double precioAlquiler;

    //METODOS

    public Accesorio(long _IDAccesorio, String _descripcion, double _precioAlquiler){
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
    public static ArrayList<Accesorio> cargaXML(String xmlFilePath) {
        try {
            // Crear una fábrica de constructores de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(new File(xmlFilePath));

            // Obtener la lista de nodos de accesorio
            NodeList nodeList = document.getElementsByTagName("Accesorio");

            // Crear una lista de accesorios
            ArrayList<Accesorio> accesorios = new ArrayList<>();

            // Recorrer cada nodo de accesorio y crear un objeto Accesorio
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    long IDAccesorio = Long.parseLong(element.getElementsByTagName("IDAccesorio").item(0).getTextContent());
                    String descripcion = element.getElementsByTagName("descripcion").item(0).getTextContent();
                    double precioAlquiler = Double.parseDouble(element.getElementsByTagName("precioAlquiler").item(0).getTextContent());
                    Accesorio accesorio = new Accesorio(IDAccesorio, descripcion, precioAlquiler);
                    accesorios.add(accesorio);
                }
            }
            return accesorios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}