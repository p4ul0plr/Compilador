/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractSyntaxTree;

/**
 *
 * @author paulo
 */
public class NodeTermo {

    public NodeFator nodeFator;
    public NodeTermoComplemento nodeTermoComplemento;
    public byte kind;
    
    public void visit(Visitor v) {
        v.visitTermo(this);
    }
}
