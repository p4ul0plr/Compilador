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
public class NodeExpressao extends NodeFator{

    public NodeExpressaoSimples nodeExpressaoSimples1, nodeExpressaoSimples2;
    public NodeOpRel nodeOpRel;

    public NodeExpressao(NodeExpressaoSimples nodeExpressaoSimples1, NodeExpressaoSimples nodeExpressaoSimples2, NodeOpRel nodeOpRel) {
        this.nodeExpressaoSimples1 = nodeExpressaoSimples1;
        this.nodeExpressaoSimples2 = nodeExpressaoSimples2;
        this.nodeOpRel = nodeOpRel;
    }

    public NodeExpressao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Visitor v) {
        v.visitExpressao(this);
    }
}
