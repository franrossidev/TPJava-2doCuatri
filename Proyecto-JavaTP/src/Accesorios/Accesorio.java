package Accesorios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;

public class Accesorio {

    private final long IDAccesorio;
    private final String descripcion;
    private final double precioAlquiler;

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

    public String mostrar() {
        return "ID Accesorio: " + this.IDAccesorio + "\n" +
                "Descripción: " + this.descripcion + "\n" +
                "Precio de alquiler: " + this.precioAlquiler + "\n";
    }
    public static HashSet<Accesorio> cargaXML(String xmlFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));

            NodeList nodeList = document.getElementsByTagName("accesorio");
            HashSet<Accesorio> accesorios = new HashSet<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    long IDAccesorio = Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent());
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