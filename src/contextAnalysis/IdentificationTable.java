/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalysis;

import abstractSyntaxTree.NodeId;
import java.util.ArrayList;
import lexicalAnalysis.Token;

/**
 *
 * @author paulo
 */
public class IdentificationTable {

    private final ArrayList<NodeId> identificadores = new ArrayList<>();
    private static int i = 0;
    private boolean naoEstaContido = true;

//    public void enter(Token id) {
//        if (!identificadores.isEmpty()) {
//            /*for (StringBuffer identificadore : identificadores) {
//                if (id.equals(identificadore)) {
//                    System.out.println("Identificador " + id + " já foi declarado!");
//                    naoEstaContido = false;
//                }
//            }*/
//            for (int j = 0; j < identificadores.size(); j++) {
//                if (id.spelling.equals(identificadores.get(j).spelling)) {
//                    System.out.println("CONTEXT ERROR! -"
//                            + " LINE: " + id.getLine()
//                            + " COLUMN: " + id.getColumn()
//                            + " - Identifier " + id.getSpelling() + " already declared!");
//                    naoEstaContido = false;
//                }
//            }
//        }
//        if (naoEstaContido) {
//            identificadores.add(i, id);
//            //System.out.println(identificadores.get(i));
//            i++;
//        }
//        naoEstaContido = true;
//        /*if (this.identificadores.contains(id)) {
//            System.out.println("Identificador " + id + " já foi declarado!");
//        } else {
//            this.identificadores.add(id);
//            //System.out.println(this.identificadores.get(i));
//        }*/
//        //i++;
//    }
    
    public void enter(NodeId id) {
        if (!identificadores.isEmpty()) {
            /*for (StringBuffer identificadore : identificadores) {
                if (id.equals(identificadore)) {
                    System.out.println("Identificador " + id + " já foi declarado!");
                    naoEstaContido = false;
                }
            }*/
            for (int j = 0; j < identificadores.size(); j++) {
                if (id.spelling.equals(identificadores.get(j).spelling)) {
                    System.out.println("CONTEXT ERROR! -"
                            + " LINE: " + id.getLine()
                            + " COLUMN: " + id.getColumn()
                            + " - Identifier " + id.getSpelling() + " already declared!");
                    naoEstaContido = false;
                }
            }
        }
        if (naoEstaContido) {
            identificadores.add(i, id);
            //System.out.println(identificadores.get(i));
            i++;
        }
        naoEstaContido = true;
        /*if (this.identificadores.contains(id)) {
            System.out.println("Identificador " + id + " já foi declarado!");
        } else {
            this.identificadores.add(id);
            //System.out.println(this.identificadores.get(i));
        }*/
        //i++;
    }

//    public void retrieve(Token id) {
//        /*if (!this.identificadores.contains(id)) {
//            System.out.println("Identificador " + id + " não declarado!");
//        }*/
//        if (!identificadores.isEmpty()) {
//            for (int j = 0; j < identificadores.size(); j++) {
//                if (!id.spelling.equals(identificadores.get(j).spelling)) {
//                    naoEstaContido = true;
//                } else {
//                    naoEstaContido = false;
//                    break;
//                }
//            }
//            if (naoEstaContido) {
//                System.out.println("CONTEXT ERROR! -"
//                        + " LINE: " + id.getLine()
//                        + " COLUMN: " + id.getColumn()
//                        + " - Identifier " + id.getSpelling() + " not declared!");
//            }
//        }
//    }
    
    public byte retrieve(NodeId id) {
        /*if (!this.identificadores.contains(id)) {
            System.out.println("Identificador " + id + " não declarado!");
        }*/
        if (!identificadores.isEmpty()) {
            for (int j = 0; j < identificadores.size(); j++) {
                if (!id.spelling.equals(identificadores.get(j).spelling)) {
                    naoEstaContido = true;
                } else {
                    naoEstaContido = false;
                    return id.kind;
                    //break;
                }
            }
            if (naoEstaContido) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + id.getLine()
                        + " COLUMN: " + id.getColumn()
                        + " - Identifier " + id.getSpelling() + " not declared!");
            }
        }
        return -1;
    }
}
