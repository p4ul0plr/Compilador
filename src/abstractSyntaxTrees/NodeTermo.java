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
public class NodeTermo {

    public NodeFator nodeFator;
    public NodeTermoComplemento nodeTermoComplemento;
    
    public NodeTermo(NodeFator nodeFator, NodeTermoComplemento nodeTermoComplemento) {
        this.nodeFator = nodeFator;
        this.nodeTermoComplemento = nodeTermoComplemento;
    }

    public NodeTermo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void visit(Visitor v) {
        v.visitTermo(this);
    }
}
