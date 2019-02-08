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
public class NodeCorpo {
    
    public NodeDeclaracoes nodeDeclaracoes;
    public NodeComandoComposto nodeComandoComposto;
    
    public NodeCorpo(NodeDeclaracoes nodeDeclaracoes, NodeComandoComposto nodeComandoComposto) {
        this.nodeDeclaracoes = nodeDeclaracoes;
        this.nodeComandoComposto = nodeComandoComposto;
    }

    public NodeCorpo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void visit(Visitor v) {
        v.visitCorpo(this);
    }
}
