/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractSyntaxTree;

import lexicalAnalysis.Token;

/**
 *
 * @author paulo
 */
public class NodeOpRel extends Token {

    /*public String opRel;
    
    public NodeOpRel(String opRel) {
        this.opRel = opRel;
    }*/
    
    public NodeOpRel(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitOpRel(this);
    }
}
