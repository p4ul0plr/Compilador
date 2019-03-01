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
public class NodeTipoAgregado extends NodeTipo {
    
    public NodeLiteral nodeLiteral1, nodeLiteral2;
    public NodeTipo nodeTipo;
    
    @Override
    public void visit(Visitor v) {
        v.visitTipoAgregado(this);
    }
}
