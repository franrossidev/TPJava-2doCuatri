package Stands;

import Accesorios.Accesorio;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class Stands extends JDialog {
    private JPanel contentPane;
    private JButton BottonSiguiente;
    private JButton buttonAnterior;
    private JTextArea textArea1;
    private JTextArea Numeracion;
    private Stand standActual;
    private final ArrayList<Stand> liststands;
    private final HashSet<Accesorio> listaaccesorios;

    public Stands(ArrayList<Stand> lstands, HashSet<Accesorio> laccesorios) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(BottonSiguiente);
        setTitle("Stands");

        BottonSiguiente.addActionListener(e -> onSiguiente());
        buttonAnterior.addActionListener(e -> onAnterior());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });


        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        liststands = lstands;
        liststands.sort(Comparator.comparing(Stand::getIDStand));
        listaaccesorios = laccesorios;

        mostrar();
    }
    private void mostrar(){
        if(liststands != null){
            standActual = liststands.get(0);
            mostrarStand(standActual);
            Numeracion.setText(("1") + "/" + liststands.size());
        }

    }
    private void onAnterior() {
        if (standActual != null ) {
            int index = liststands.indexOf(standActual);
            if (index > 0) {
                Stand anteriorStand = liststands.get(index - 1);
                mostrarStand(anteriorStand);
                standActual = anteriorStand;
                Numeracion.setText((index) + "/" + liststands.size());
            }
        }
    }

    private void onSiguiente() {
        if (standActual != null) {
            int index = liststands.indexOf(standActual);
            if (index < liststands.size() - 1) {
                Stand siguienteStand = liststands.get(index + 1);
                mostrarStand(siguienteStand);
                standActual = siguienteStand;
                Numeracion.setText((index + 2) + "/" + liststands.size());
            }
        }
    }
    private void mostrarStand(Stand stand) {
        textArea1.setText(stand.mostrar(listaaccesorios));
    }
}
