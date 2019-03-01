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
public class NodeOpRel {

    public StringBuffer opRel;
    
    public NodeOpRel(StringBuffer opRel) {
        this.opRel = opRel;
    }

    public void visit(Visitor v) {
        v.visitOpRel(this);
    }
}
