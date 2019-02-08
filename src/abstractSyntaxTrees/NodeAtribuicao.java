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
public class NodeAtribuicao extends NodeComando {

    public NodeVariavel nodeVariavel;
    public NodeExpressao nodeExpressao;

    public NodeAtribuicao(NodeVariavel nodeVariavel, NodeExpressao nodeExpressao) {
        this.nodeVariavel = nodeVariavel;
        this.nodeExpressao = nodeExpressao;
    }

    public NodeAtribuicao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void visit(Visitor v) {
        v.visitAtribuicao(this);

    }
}
