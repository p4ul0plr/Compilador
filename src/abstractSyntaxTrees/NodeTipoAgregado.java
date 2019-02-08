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
public class NodeTipoAgregado extends NodeTipo {
    
    public NodeLiteral nodeLiteral1, nodeLiteral2;
    public NodeTipo nodeTipo;
    
    public NodeTipoAgregado(NodeLiteral nodeLiteral1, NodeLiteral nodeLiteral2, NodeTipo nodeTipo) {
        this.nodeLiteral1 = nodeLiteral1;
        this.nodeLiteral2 = nodeLiteral2;
        this.nodeTipo = nodeTipo;
    }

    public NodeTipoAgregado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitTipoAgregado(this);
    }
}
