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
public class NodeOpRel {

    public char opRel;
    
    public NodeOpRel(char opRel) {
        this.opRel = opRel;
    }

    public void visit(Visitor v) {
        v.visitOpRel(this);
    }
}
