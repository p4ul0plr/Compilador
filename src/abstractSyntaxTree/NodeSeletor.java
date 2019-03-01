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
public class NodeSeletor {

    public NodeExpressao nodeExpressao;
    public NodeSeletor next;
    
    public void visit(Visitor v) {
        v.visitSeletor(this);
    }
}
