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
public class NodeComandoComposto extends NodeComando {
    
    public NodeListaDeComandos nodeListaDeComandos;
    
    public NodeComandoComposto(NodeListaDeComandos nodeListaDeComandos) {
        this.nodeListaDeComandos = nodeListaDeComandos;
    }

    public NodeComandoComposto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitComandoComposto(this);
    }
}
