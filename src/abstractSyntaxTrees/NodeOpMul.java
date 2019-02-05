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
public class NodeOpMul {

    public char opMul;
    
    public NodeOpMul(char opMul) {
        this.opMul = opMul;
    }

    public void visit(Visitor v) {
        v.visitOpMul(this);
    }
}
