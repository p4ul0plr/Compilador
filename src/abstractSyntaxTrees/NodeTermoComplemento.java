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
public class NodeTermoComplemento {

    public NodeOpMul nodeOpMul;
    public NodeFator nodeFator;
    public NodeTermoComplemento next;
    
    public void visit(Visitor v) {
        v.visitTermoComplemento(this);
    }
}
