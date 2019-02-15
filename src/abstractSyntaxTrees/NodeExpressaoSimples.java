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
public class NodeExpressaoSimples {
    
    public NodeTermo nodeTermo;
    public NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento;
    
    public NodeExpressaoSimples(NodeTermo nodeTermo, NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        this.nodeTermo = nodeTermo;
        this.nodeExpressaoSimplesComplemento = nodeExpressaoSimplesComplemento;
    }

    public NodeExpressaoSimples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void visit(Visitor v) {
        v.visitExpressaoSimples(this);
    }
    
}
