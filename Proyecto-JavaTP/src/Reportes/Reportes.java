package Reportes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Stands.Stand;

public class Reportes {
    private ArrayList<Stand> listaStandsActualizada = new ArrayList<>();

    // Metodos
    public void reporteStands(ArrayList<Stand> listaStands){
        listaStandsActualizada=listaStands;
        double valorPromedio=0;
        int i=1;
        StringBuilder texto = new StringBuilder();
        //Ordeno la lista de Stands Actualizada comparando los valores de los stands descendentemente.
        Collections.sort(listaStandsActualizada, new Comparator<Stand>(){
            @Override
            public int compare(Stand s1, Stand s2) {
                return new Double(s1.Valor()).compareTo(new Double(s2.Valor()));
            }
        });
        // Armo el texto con el Listado
        texto.append("Listado de Stands (Descendentemente x valor)\n");
        for (Stand stand : listaStandsActualizada) {
            texto.append("Stand: #" + i + " Valor: $ " + stand.Valor() + "\n");
            valorPromedio+=stand.Valor();
        }
        texto.append("-- Valor Promedio del Stand: $ " + valorPromedio/listaStandsActualizada.size() + "--\n");
    }
}