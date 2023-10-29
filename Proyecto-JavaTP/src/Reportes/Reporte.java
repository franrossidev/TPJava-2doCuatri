package Reportes;

import javax.swing.*;

import Accesorios.Accesorio;
import Stands.Stand;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Reporte extends JDialog {
    private JPanel contentPane;

    private JButton bottonGuardar;
    private JTextPane textPaneStand;
    private JTextPane textPaneAccesorios;

    public Reporte(ArrayList<Stand> listaS, HashSet<Accesorio> listaA) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bottonGuardar);

        bottonGuardar.addActionListener(new ActionListener() {
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
        setPanels(listaS, listaA);
    }

    private void onGuardar() {
        // add your code here if necessary

    }

    public void setPanels(ArrayList<Stand> listaS, HashSet<Accesorio> listaA){
        textPaneStand.setText(reporteStands(listaS, listaA));
        textPaneAccesorios.setText(reporteAccesorios(listaA, listaS));
    }
    
    // Reporte de los Stands
    public String reporteStands(ArrayList<Stand> listaStands, HashSet<Accesorio> listaAccesorios){
        ArrayList<Stand> listaStandsActualizada=listaStands;
        double valorPromedio=0;
        int i=1;
        String texto="";
        //Ordeno la lista de Stands Actualizada comparando los valores de los stands descendentemente.
        Collections.sort(listaStandsActualizada, new Comparator<Stand>(){
            @Override
            public int compare(Stand s1, Stand s2) {
               return new Double(s2.Valor(listaAccesorios)).compareTo(new Double(s1.Valor(listaAccesorios)));
            }
        });
        // Armo el texto con el Listado de Stands
        texto+="Listado de Stands (Descendentemente x valor)\n";
        for (Stand stand : listaStandsActualizada) {
            texto+="Stand: #" + i + " Valor: $ " + stand.Valor(listaAccesorios) + "\n";
            valorPromedio+=stand.Valor(listaAccesorios);
        }
        texto+="-- Valor Promedio del Stand: $ " + valorPromedio/listaStandsActualizada.size() + "--\n";

        return texto;
    }

    //Reporte de Accesorios
    public String reporteAccesorios(HashSet<Accesorio> listAccesorios, ArrayList<Stand> listaStands){
        int i=1;
        String texto="";

        //Armo el texto con el Listado de Accesorios
        texto+="Listado de Accesorios\n";
        for (Accesorio accesorio : listAccesorios) {
            int sumaUsos=0;
            texto+="Accesorio: " + accesorio.getIDAccesorio() + " Descripcion: "+ accesorio.getDescripcion() + " Precio: $ " + accesorio.getPrecioAlquiler() + "\n";
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
        }    

        return texto;
    }
}
