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
    private JButton buttonBuscar;
    private JTextArea textArea1;
    private JComboBox clienteComboBox;
    private final ArrayList<Cliente> listaclientes;
    private final ArrayList<Accesorio> listaaccesorios;
    private final ArrayList<Stand> listastands;

    public Principal() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBuscar);

        String xmlFilePath = "src/Cliente/clientes.xml";
        listaclientes = Cliente.cargaXML(xmlFilePath);

        for (Cliente cliente : listaclientes) {
            clienteComboBox.addItem(String.valueOf(cliente.getIDCliente()));
        }
        buttonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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


        xmlFilePath = "src/Accesorios/accesorios.xml";
        listaaccesorios = Accesorio.cargaXML(xmlFilePath);

        xmlFilePath = "src/Stands/stands.xml";
        listastands = loadFromXMLstand(xmlFilePath);

        mostrarStand(listastands.get(0));
    }

    private void onOK() {
        // add your code here
        long idCliente = Long.parseLong((String) clienteComboBox.getSelectedItem());
        textArea1.setText(listastands.get((int) idCliente - 1).mostrar(listaaccesorios));
    }
    private void mostrarStand(Stand stand) {
        textArea1.setText(stand.mostrar(listaaccesorios));
    }
    public static ArrayList<Stand> loadFromXMLstand(String xmlFilePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            NodeList nodeList = document.getElementsByTagName("stand");
            ArrayList<Stand> stands = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
                    int superficie = Integer.parseInt(element.getElementsByTagName("superficie").item(0).getTextContent());
                    double precioM2 = Double.parseDouble(element.getElementsByTagName("precioM2").item(0).getTextContent());
                    long idCliente = Long.parseLong(element.getElementsByTagName("idCliente").item(0).getTextContent());

                    // Carga de accesorios utilizando la función cargaXML de la clase Accesorio
                    NodeList accesoriosNodeList = element.getElementsByTagName("accesorios");
                    ArrayList<Long> accesorios = new ArrayList<>();
                    for (int j = 0; j < accesoriosNodeList.getLength(); j++) {
                        Node accesoriosNode = accesoriosNodeList.item(j);
                        if (accesoriosNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element accesoriosElement = (Element) accesoriosNode;
                            NodeList accesorioNodeList = accesoriosElement.getElementsByTagName("accesorio");
                            for (int k = 0; k < accesorioNodeList.getLength(); k++) {
                                Element accesorioElement = (Element) accesorioNodeList.item(k);
                                long IDAccesorio = Long.parseLong(accesorioElement.getTextContent());
                                accesorios.add(IDAccesorio);
                            }
                        }
                    }
                    int luminarias = 0;
                    if (element.getElementsByTagName("luminarias").getLength() > 0) {
                        luminarias = Integer.parseInt(element.getElementsByTagName("luminarias").item(0).getTextContent());
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