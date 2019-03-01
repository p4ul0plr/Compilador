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
public class NodeDeclaracoes {

    public NodeDeclaracao nodeDeclaracao;
    public NodeDeclaracoes next;

    public NodeDeclaracoes(NodeDeclaracao nodeDeclaracao, NodeDeclaracoes next) {
        this.nodeDeclaracao = nodeDeclaracao;
        this.next = next;
    }

    public void visit(Visitor v) {
        v.visitDeclaracoes(this);
    }

}
