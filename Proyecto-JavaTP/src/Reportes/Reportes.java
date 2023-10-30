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
        setTitle("Reportes");

        buttonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onGuardar();
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

        reporteStands(listaS,listaA);
        reporteAccesorios(listaA,listaS);
    }

    public void reporteStands(ArrayList<Stand> listaStands, HashSet<Accesorio> listaAccesorios){
        ArrayList<Stand> listaStandsActualizada=listaStands;
        double valorPromedio=0;
        String texto="Listado de Stands (Descendentemente x valor)\n\n";
        Collections.sort(listaStandsActualizada, (obj1, obj2) -> Double.compare(obj1.Valor(listaAccesorios), obj2.Valor(listaAccesorios)));
        Collections.reverse(listaStandsActualizada);
        // Armo el texto con el Listado de Stands
        for (Stand stand : listaStandsActualizada) {
            texto+="Stand-ID: #" + stand.getIDStand() + "     Valor: $ " + stand.Valor(listaAccesorios) + "\n";
            valorPromedio+=stand.Valor(listaAccesorios);
        }
        String promedio = String.format("%.2f", valorPromedio/listaStandsActualizada.size());
        texto+="\n-- Valor Promedio del Stand: $ " + ((listaStandsActualizada.size() > 0) ? promedio : 0) + " --\n";//Controlo division por cero
        textAreaStand.setText(texto);
    }

    public void reporteAccesorios(HashSet<Accesorio> listAccesorios, ArrayList<Stand> listaStands){
        String texto="Listado de Accesorios\n\n";
        List<Accesorio> listaOrdenada = new ArrayList<>(listAccesorios);

        // Ordenar la lista según la descripción
        Collections.sort(listaOrdenada, new Comparator<Accesorio>() {
            @Override
            public int compare(Accesorio a1, Accesorio a2) {
                return a1.getDescripcion().compareTo(a2.getDescripcion());
            }
        });

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
    }
}
