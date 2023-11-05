package Accesorios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;


/**
 * Clase accesorio representa los accesorios disponibles para ser contratados por los stands.
 */
public class Accesorio {

    private final long IDAccesorio;
    private final String descripcion;
    private final double precioAlquiler;

    //METODOS


    /**
     * Crea un accesorio con los siguientes parametros:
     * @param _IDAccesorio identificador univoco del accesorio.
     * @param _descripcion descripcion del accesorio.
     * @param _precioAlquiler precio de alquiler del accesorio.
     */
    public Accesorio(long _IDAccesorio, String _descripcion, double _precioAlquiler){
        IDAccesorio = _IDAccesorio; descripcion = _descripcion; precioAlquiler = _precioAlquiler;
    }

    /**
     * @return devuelve el ID del accesorio
     */
    public long getIDAccesorio() {
        return IDAccesorio;
    }

    /**
     * @return devuelve el precio de alquiler
     */
    public double getPrecioAlquiler() {
            return precioAlquiler;
        }

    /**
     * @return devuelve la descripcion del accesorio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return devuelve un objeto de tipo string con el ID del accesorio, su descripcion y su precio.
     */
    public String mostrar() {
        return "ID Accesorio: " + this.IDAccesorio + "\n" +
                "Descripción: " + this.descripcion + "\n" +
                "Precio de alquiler: " + this.precioAlquiler + "\n";
    }

    /**
     * metodo que realiza la carga de datos de accesorios.
     * @param xmlFilePath es el archivo XML del cual se extraen los datos
     * @return devuelve una lista de accesorios de tipo HashSet cargada con los datos del XML proporcionado.
     */
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