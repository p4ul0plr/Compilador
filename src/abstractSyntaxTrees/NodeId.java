/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractSyntaxTrees;

/**
 *
 * @author paulo
 */
public class NodeId {
    
    public String identificador;
    
    public NodeId(String identificador) {
        this.identificador = identificador;
    }
   
    public void visit(Visitor v) {
        v.visitId(this);
    }
}
