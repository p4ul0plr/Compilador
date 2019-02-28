/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

import java.util.ArrayList;

/**
 *
 * @author paulo
 */
public class IdentificationTable {

    ArrayList<StringBuffer> identificadores = new ArrayList<>();

    public void enter(StringBuffer id) {
        if (identificadores.contains(id)) {
            System.out.println("Identificador " + id + " já foi declarado!");
        } else {
            identificadores.add(id);
        }
    }

    public void retrieve() {

    }
}
