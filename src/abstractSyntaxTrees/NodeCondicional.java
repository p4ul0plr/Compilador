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
public class NodeCondicional extends NodeComando {

    public NodeExpressao nodeExpressao;
    public NodeComando nodeComandoIf, nodeComandoElse;

    public NodeCondicional(NodeExpressao nodeExpressao, NodeComando nodeComandoIf, NodeComando nodeComandoElse) {
        this.nodeExpressao = nodeExpressao;
        this.nodeComandoIf = nodeComandoIf;
        this.nodeComandoElse = nodeComandoElse;
    }

    @Override
    public void visit(Visitor v) {
        v.visitCondicional(this);
    }
}
