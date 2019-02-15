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
public class NodeIntLit extends NodeLiteral {

    public String intLiteral;
    
    public NodeIntLit(String intLiteral) {
        this.intLiteral = intLiteral;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitIntLit(this);
    }
}
