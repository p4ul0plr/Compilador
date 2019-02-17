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
    
    @Override
    public void visit(Visitor v) {
        v.visitComandoComposto(this);
    }
}
