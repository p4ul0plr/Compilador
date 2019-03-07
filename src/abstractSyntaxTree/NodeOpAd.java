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
public class NodeOpAd extends Token {

    /*public String opAd;
    
    public NodeOpAd(String opAd) {
        this.opAd = opAd;
    }*/
    
    
    public NodeOpAd(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitOpAd(this);
    }
}
