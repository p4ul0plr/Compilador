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
public class NodeExpressaoSimplesComplemento {
    
    public NodeOpAd nodeOpAd;
    public NodeTermo nodeTermo;
    public NodeExpressaoSimplesComplemento next;
    
    public NodeExpressaoSimplesComplemento(NodeOpAd nodeOpAd, NodeTermo nodeTermo, NodeExpressaoSimplesComplemento next) {
        this.nodeOpAd = nodeOpAd;
        this.nodeTermo = nodeTermo;
        this.next = next;
    }

    public NodeExpressaoSimplesComplemento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void visit(Visitor v) {
        v.visitExpressaoSimplesComplemento(next);
    }
}
