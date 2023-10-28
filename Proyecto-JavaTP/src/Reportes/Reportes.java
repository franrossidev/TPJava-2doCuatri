package Reportes;

import Accesorios.Accesorio;
import Stands.Stand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Reportes {
    private ArrayList<Stand> listaStandsActualizada;
    private  ArrayList<Accesorio> listaAccesoriosActualizada;
    // Metodos
    Reportes(ArrayList<Stand> listaS, ArrayList<Accesorio> listaA){
        listaStandsActualizada=reporteStands(listaS,listaA);
        listaAccesoriosActualizada=reporteAccesorios(listaA,listaS);
    }

    // Reporte de los Stands
    public ArrayList<Stand> reporteStands(ArrayList<Stand> listaStands, ArrayList<Accesorio> listaAccesorios){

        double valorPromedio=0;
        int i=1;
        StringBuilder texto = new StringBuilder();
        //Ordeno la lista de Stands Actualizada comparando los valores de los stands descendentemente.
        Collections.sort(listaStandsActualizada, new Comparator<Stand>(){
            @Override
            public int compare(Stand s1, Stand s2) {
               return new Double(s1.Valor(listaAccesorios)).compareTo(new Double(s2.Valor(listaAccesorios)));
            }
        });
        // Armo el texto con el Listado de Stands
        texto.append("Listado de Stands (Descendentemente x valor)\n");
        for (Stand stand : listaStandsActualizada) {
            texto.append("Stand: #" + i + " Valor: $ " + stand.Valor(listaAccesorios) + "\n");
            valorPromedio+=stand.Valor(listaAccesorios);
        }
        texto.append("-- Valor Promedio del Stand: $ " + valorPromedio/listaStandsActualizada.size() + "--\n");

        return listaStandsActualizada;
    }

    //Reporte de Accesorios
    public ArrayList<Accesorio> reporteAccesorios(ArrayList<Accesorio> listAccesorios, ArrayList<Stand> listaStands){
        int i=1;
        StringBuilder texto = new StringBuilder();

        //Armo el texto con el Listado de Accesorios
        texto.append("Listado de Accesorios\n");
        for (Accesorio accesorio : listAccesorios) {
            int sumaUsos=0;
            texto.append("Accesorio: " + accesorio.getIDAccesorio() + " Descripcion: "+ accesorio.getDescripcion() + " Precio: $ " + accesorio.getPrecioAlquiler() + "\n");
            for (Stand stand : listaStands) {
                ArrayList<Long> standLista=stand.getListaAccesorios();
                if(standLista!=null){
                    for (long acc : standLista) {
                        if(acc==accesorio.getIDAccesorio()){
                            sumaUsos++;
                        }
                    }
                }
            }
            texto.append("Cantidad de usos en los stands: " + sumaUsos + "\n")
        }    

        return listaAccesoriosActualizada;
    }
}