package Reportes;

import Accesorios.Accesorio;
import Stands.Stand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Reportes {
    private ArrayList<Stand> listaStandsActualizada = new ArrayList<>();
    private  ArrayList<Accesorio> listaaccesorios;
    // Metodos
    public void reporteStands(ArrayList<Stand> listaStands){

        String xmlFilePath = "src/Accesorios/accesorios.xml";
        listaaccesorios = Accesorio.cargaXML(xmlFilePath);
        listaStandsActualizada=listaStands;
        double valorPromedio=0;
        int i=1;
        StringBuilder texto = new StringBuilder();
        //Ordeno la lista de Stands Actualizada comparando los valores de los stands descendentemente.
        Collections.sort(listaStandsActualizada, new Comparator<Stand>(){
            @Override
            public int compare(Stand s1, Stand s2) {
               // return new Double(s1.Valor(listaaccesorios)).compareTo(new Double(s2.Valor(listaaccesorios)));
                return 0;
            }
        });
        // Armo el texto con el Listado
        texto.append("Listado de Stands (Descendentemente x valor)\n");
        for (Stand stand : listaStandsActualizada) {
            texto.append("Stand: #" + i + " Valor: $ " + stand.Valor(listaaccesorios) + "\n");
            valorPromedio+=stand.Valor(listaaccesorios);
        }
        texto.append("-- Valor Promedio del Stand: $ " + valorPromedio/listaStandsActualizada.size() + "--\n");
    }
}