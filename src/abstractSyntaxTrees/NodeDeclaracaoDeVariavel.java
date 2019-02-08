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
public class NodeDeclaracaoDeVariavel {
    
    public NodeListaDeIds nodeListaDeIds;
    public NodeTipo nodeTipo;
    
    public NodeDeclaracaoDeVariavel(NodeListaDeIds nodeListaDeIds, NodeTipo nodeTipo) {
        this.nodeListaDeIds = nodeListaDeIds;
        this.nodeTipo = nodeTipo;
    }

    public NodeDeclaracaoDeVariavel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void visit(Visitor v) {
        v.visitDeclaracaoDeVariavel(this);
    }
}
