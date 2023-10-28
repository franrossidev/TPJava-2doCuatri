package Reportes;

import javax.swing.*;
import java.awt.event.*;

public class Reporte extends JDialog {
    private JPanel contentPane;

    private JButton bottonGuardar;
    private JTextPane textPaneStand;
    private JTextPane textPaneAccesorios;

    public Reporte() {
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
        textPaneStand.setText("Stand");
        textPaneAccesorios.setText("Accesorio");
    }

    private void onGuardar() {
        // add your code here if necessary

    }


}
