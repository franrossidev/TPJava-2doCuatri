package Reportes;

import Accesorios.Accesorio;
import Stands.Stand;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Reportes extends JDialog {
    private JPanel contentPane;
    private JButton buttonGuardar;
    private JTextArea textAreaStand;
    private JTextArea textAreaAccesorio;

    public Reportes(ArrayList<Stand> listaS, HashSet<Accesorio> listaA) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonGuardar);
        setTitle("Reporte");

        buttonGuardar.addActionListener(e -> onGuardar());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        reporteStands(listaS,listaA);
        reporteAccesorios(listaA,listaS);
    }

    public void reporteStands(ArrayList<Stand> listaStands, HashSet<Accesorio> listaAccesorios){
        double valorPromedio=0;
        String texto="Listado de Stands (Descendentemente x valor)\n\n";
        listaStands.sort(Comparator.comparingDouble(obj -> obj.Valor(listaAccesorios)));
        Collections.reverse(listaStands);
        // Armo el texto con el Listado de Stands
        for (Stand stand : listaStands) {
            texto+="Stand-ID: #" + stand.getIDStand() + "     Valor: $ " + stand.Valor(listaAccesorios) + "\n";
            valorPromedio+=stand.Valor(listaAccesorios);
        }
        String promedio = String.format("%.2f", valorPromedio/ listaStands.size());
        texto+="\n-- Valor Promedio del Stand: $ " + ((listaStands.size() > 0) ? promedio : 0) + " --\n";//Controlo division por cero
        textAreaStand.setText(texto);
    }

    public void reporteAccesorios(HashSet<Accesorio> listAccesorios, ArrayList<Stand> listaStands){
        String texto="Listado de Accesorios\n\n";
        List<Accesorio> listaOrdenada = new ArrayList<>(listAccesorios);

        // Ordenar la lista según la descripción
        listaOrdenada.sort(Comparator.comparing(Accesorio::getDescripcion));

        //Armo el texto con el Listado de Accesorios
        for (Accesorio accesorio : listaOrdenada) {
            int sumaUsos=0;
            texto+="Accesorio: " + accesorio.getIDAccesorio() + "   Descripcion: "+ accesorio.getDescripcion() + "   Precio: $ " + accesorio.getPrecioAlquiler() + "\n";
            for (Stand stand : listaStands) {
                HashSet<Long> standLista=stand.getListaAccesorios();
                if(standLista!=null){
                    for (long acc : standLista) {
                        if(acc==accesorio.getIDAccesorio()){
                            sumaUsos++;
                        }
                    }
                }
            }
            texto+="Cantidad de usos en los stands: " + sumaUsos + "\n";
            texto+="-----------------------------------------------------------------------------------\n";
        }

        textAreaAccesorio.setText(texto);
    }
    private void onGuardar() {
        //Reporte Accesorio
        String rutaArchivo="src/Reportes/ReporteAccesorios.txt";
        File archivo = new File(rutaArchivo);
        String texto=textAreaAccesorio.getText();
        try {
            // Creo objeto para escribir
            FileWriter escribir = new FileWriter(archivo);

            // Escribo el archivo
            escribir.write(texto);

            // Cierro objeto para escribir
            escribir.close();
            System.out.println("Archivo de reporte de accesorios creado con exito.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Reporte Stand
        String rutaArchivo2="src/Reportes/ReporteStands.txt";
        File archivo2 = new File(rutaArchivo2);
        String texto2=textAreaStand.getText();
        try {
            // Creo objeto para escribir
            FileWriter escribir2 = new FileWriter(archivo2);

            // Escribo el archivo
            escribir2.write(texto2);

            // Cierro objeto para escribir
            escribir2.close();
            System.out.println("Archivo de reporte de stands creado con exito.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "El archivo se a guardado correctamente", "PopUp Dialog", JOptionPane.INFORMATION_MESSAGE);
    }
}
