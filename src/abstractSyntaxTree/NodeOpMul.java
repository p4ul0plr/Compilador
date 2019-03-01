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
public class NodeOpMul {

    public StringBuffer opMul;
    
    public NodeOpMul(StringBuffer opMul) {
        this.opMul = opMul;
    }

    public void visit(Visitor v) {
        v.visitOpMul(this);
    }
}
