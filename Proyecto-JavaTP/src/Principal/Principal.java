package Principal;


import Accesorios.Accesorio;
import Cliente.Cliente;
import Reportes.Reportes;
import Stands.Exterior;
import Stands.Interior;
import Stands.Stand;
import Stands.Stands;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Principal extends JDialog {
    private JPanel contentPane;
    private JButton buttonBuscar;
    private JTextArea textArea1;
    private JComboBox<String> clienteComboBox;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JTextArea Numeracion;
    private JButton reporteButton;
    private JButton standsButton;
    private final ArrayList<Cliente> listaclientes;
    private final HashSet<Accesorio> listaaccesorios;
    private final ArrayList<Stand> listastands;
    private Stand standActual;
    private ArrayList<Stand> standsCliente;

    public Principal() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonBuscar);
        setTitle("Principal");



        standsButton.addActionListener(e -> onStands());
        reporteButton.addActionListener(e -> onReporte());
        buttonBuscar.addActionListener(e -> onBuscar());
        anteriorButton.addActionListener(e -> onAnterior());
        siguienteButton.addActionListener(e -> onSiguiente());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        //Lectura de archivos XMLs

        anteriorButton.setVisible(false);
        siguienteButton.setVisible(false);
        Numeracion.setVisible(false);

        String xmlFilePath = "src/Cliente/clientes.xml";
        listaclientes = Cliente.cargaXML(xmlFilePath);

        assert listaclientes != null;
        for (Cliente cliente : listaclientes) {
            clienteComboBox.addItem(String.valueOf(cliente.getDescripcion()));
        }
        xmlFilePath = "src/Accesorios/accesorios.xml";
        listaaccesorios = Accesorio.cargaXML(xmlFilePath);

        xmlFilePath = "src/Stands/stands.xml";
        listastands = loadFromXMLstand(xmlFilePath);
        if (listastands != null) {
            listastands.sort(Comparator.comparing(Stand::getIDCliente));
        }
        textArea1.setText("Cliente no seleccionado.");
    }

    private void onStands(){

        Stands stands = new Stands(listastands,listaaccesorios);
        stands.pack();
        stands.setVisible(true);

    }
    private void onReporte(){
        Reportes reportes = new Reportes(listastands,listaaccesorios);
        reportes.pack();
        reportes.setVisible(true);
    }
    private void onBuscar() {
        Numeracion.setVisible(true);
        anteriorButton.setVisible(true);
        siguienteButton.setVisible(true);
        String descripcionSeleccionada = (String) clienteComboBox.getSelectedItem();
        long idCliente = obtenerIdClientePorDescripcion(descripcionSeleccionada);

        if (idCliente != -1) {
            standsCliente = obtenerStandsPorIdCliente(idCliente);

            if (!standsCliente.isEmpty()) {
                standActual = standsCliente.get(0);
                mostrarStand(standActual);
                Numeracion.setText( "1/" + standsCliente.size());
            } else {
                textArea1.setText("No se encontró ningún stand para el cliente especificado.");
            }
        } else {
            textArea1.setText("No se encontró el cliente con la descripción especificada.");
        }

    }

    private void onAnterior() {
        if (standActual != null && standsCliente != null) {
            int index = standsCliente.indexOf(standActual);
            if (index > 0) {
                Stand anteriorStand = standsCliente.get(index - 1);
                mostrarStand(anteriorStand);
                standActual = anteriorStand;
                Numeracion.setText((index) + "/" + standsCliente.size());
            }
        }
    }

    private void onSiguiente() {
        if (standActual != null && standsCliente != null) {
            int index = standsCliente.indexOf(standActual);
            if (index < standsCliente.size() - 1) {
                Stand siguienteStand = standsCliente.get(index + 1);
                mostrarStand(siguienteStand);
                standActual = siguienteStand;
                Numeracion.setText((index + 2) + "/" + standsCliente.size());
            }
        }
    }
    private void mostrarStand(Stand stand) {
        textArea1.setText(stand.mostrar(listaaccesorios)); //POLIMORFISMO CON METODO mostrar
    }

    private long obtenerIdClientePorDescripcion(String descripcion) {
        for (Cliente cliente : listaclientes) {
            if (cliente.getDescripcion().equals(descripcion)) {
                return cliente.getIDCliente();
            }
        }
        return -1; // Retorna -1 si no se encuentra el cliente
    }
    private ArrayList<Stand> obtenerStandsPorIdCliente(long idCliente) {
        ArrayList<Stand> standsPorCliente = new ArrayList<>();

        for (Stand stand : listastands) {
            if (stand.getIDCliente() == idCliente) {
                standsPorCliente.add(stand);
            }
        }

        return standsPorCliente;
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
                    HashSet<Long> accesorios = new HashSet<>();
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
