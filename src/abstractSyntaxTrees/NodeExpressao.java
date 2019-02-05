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
public class NodeExpressao {

    public NodeExpressaoSimples nodeExpressaoSimples1, nodeExpressaoSimples2;
    public NodeOpAd nodeOpAd;

    public NodeExpressao(NodeExpressaoSimples nodeExpressaoSimples1, NodeExpressaoSimples nodeExpressaoSimples2, NodeOpAd nodeOpAd) {
        this.nodeExpressaoSimples1 = nodeExpressaoSimples1;
        this.nodeExpressaoSimples2 = nodeExpressaoSimples2;
        this.nodeOpAd = nodeOpAd;
    }

    public void visit(Visitor v) {
        v.visitExpressao(this);
    }
}
