package Principal;

import Accesorios.Accesorio;
import Cliente.Cliente;
import Stands.Exterior;
import Stands.Interior;
import Stands.Stand;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Principal extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private final ArrayList<Cliente> listaclientes;
    private final ArrayList<Accesorio> listaaccesorios;
    private final ArrayList<Stand> listastands;

    public Principal() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        String xmlFilePath = "src/Cliente/clientes.xml"; // Reemplaza con la ubicación de tu archivo XML
        listaclientes = Cliente.cargaXML(xmlFilePath);

        xmlFilePath = "src/Accesorios/accesorios.xml";
        listaaccesorios = Accesorio.cargaXML(xmlFilePath);

        xmlFilePath = "src/Stands/stands.xml";
        listastands = loadFromXMLstand(xmlFilePath);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //Lectura de archivos XMLs

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static ArrayList<Stand> loadFromXMLstand(String xmlFilePath) {
        try {
            // Crear una fábrica de constructores de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(new File(xmlFilePath));

            // Obtener la lista de nodos de stand
            NodeList nodeList = document.getElementsByTagName("stand");

            // Crear una lista de stands
            ArrayList<Stand> stands = new ArrayList<>();

            // Recorrer cada nodo de stand y crear un objeto Stand
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
                    int superficie = Integer.parseInt(element.getElementsByTagName("superficie").item(0).getTextContent());
                    double precioM2 = Double.parseDouble(element.getElementsByTagName("precioM2").item(0).getTextContent());
                    long idCliente = Long.parseLong(element.getElementsByTagName("idCliente").item(0).getTextContent());
                    NodeList accesoriosNodeList = element.getElementsByTagName("accesorio");
                    int luminarias = 0;
                    ArrayList<Accesorio> accesorios = new ArrayList<>();
                    for (int j = 0; j < accesoriosNodeList.getLength(); j++) {
                        Node accesorioNode = accesoriosNodeList.item(j);
                        if (accesorioNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element accesorioElement = (Element) accesorioNode;
                            long IDAccesorio = Long.parseLong(accesorioElement.getElementsByTagName("IDAccesorio").item(0).getTextContent());
                            String descripcion = accesorioElement.getElementsByTagName("descripcion").item(0).getTextContent();
                            double precioAlquiler = Double.parseDouble(accesorioElement.getElementsByTagName("precioAlquiler").item(0).getTextContent());
                            accesorios.add(new Accesorio(IDAccesorio, descripcion, precioAlquiler));
                        }
                    }

                    // Crear el objeto Stand según el tipo y la presencia de luminarias
                    if (luminarias > 0) {
                        stands.add(new Interior(codigo, superficie, precioM2, idCliente, accesorios, luminarias));
                    } else {
                        stands.add(new Exterior(codigo, superficie, precioM2, idCliente, accesorios));
                    }
                }
            }
            return stands;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
