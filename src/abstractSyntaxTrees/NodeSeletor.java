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
public class NodeSeletor {

    public NodeExpressao nodeExpressao;
    public NodeSeletor next;
    
    public NodeSeletor(NodeExpressao nodeExpressao, NodeSeletor next) {
        this.nodeExpressao = nodeExpressao;
        this.next = next;
    }

    public NodeSeletor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void visit(Visitor v) {
        v.visitSeletor(next);
    }
}
