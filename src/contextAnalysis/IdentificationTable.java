/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalysis;

import abstractSyntaxTree.NodeVariavel;
import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class IdentificationTable {

    private final ArrayList<String> identificadores = new ArrayList<>();
    //private static int i = 0;
    //private boolean naoEstaContido = true;

    public void enter(String id) {
        /*if (!identificadores.isEmpty()) {
            for (StringBuffer identificadore : identificadores) {
                if (id.equals(identificadore)) {
                    System.out.println("Identificador " + id + " já foi declarado!");
                    naoEstaContido = false;
                }
            }
            //for (int j = 0; j < identificadores.size() + 1; j++) {
            //    if (id.equals(identificadores.get(j))) {
            //        System.out.println("Identificador " + id + " já foi declarado!");
            //        naoEstaContido = false;
            //    }
            //}
        }
        if (naoEstaContido) {
            identificadores.add(i, id);
            System.out.println(identificadores.get(i));
            i++;
        }*/

        if (this.identificadores.contains(id)) {
            System.out.println("Identificador " + id + " já foi declarado!\n");
        } else {
            this.identificadores.add(id);
            //System.out.println(this.identificadores.get(i));
        }
        //i++;
    }

    public void retrieve(String id) {
        if (!this.identificadores.contains(id)) {
            System.out.println("Identificador " + id + " não declarado!");
        }
    }
}
