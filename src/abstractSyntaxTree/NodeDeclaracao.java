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
public class NodeDeclaracao {
    
    public NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel;
    
    public NodeDeclaracao(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        this.nodeDeclaracaoDeVariavel = nodeDeclaracaoDeVariavel;
    }
    
    public void visit(Visitor v) {
        v.visitDeclaracao(this);
    }
}
