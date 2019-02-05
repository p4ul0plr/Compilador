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
public class NodeIterativo extends NodeComando {

    public NodeExpressao nodeExpressao;
    public NodeComando nodeComando;
    
    public NodeIterativo(NodeExpressao nodeExpressao, NodeComando nodeComando) {
        this.nodeExpressao = nodeExpressao;
        this.nodeComando = nodeComando;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitIterativo(this);
    }
}
