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
public class NodeCondicional extends NodeComando {

    public NodeExpressao nodeExpressao;
    public NodeComando nodeComandoIf, nodeComandoElse;

    @Override
    public void visit(Visitor v) {
        v.visitCondicional(this);
    }
}
