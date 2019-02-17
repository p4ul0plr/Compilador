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
public class NodeExpressao extends NodeFator{

    public NodeExpressaoSimples nodeExpressaoSimples1, nodeExpressaoSimples2;
    public NodeOpRel nodeOpRel;

    @Override
    public void visit(Visitor v) {
        v.visitExpressao(this);
    }
}
