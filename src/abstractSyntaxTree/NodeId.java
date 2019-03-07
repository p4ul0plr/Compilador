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
public class NodeId extends Token{
    
    /*public String identificador;
    
    public NodeId(String identificador) {
        this.identificador = identificador;
    }*/
    
    public NodeId(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public void visit(Visitor v) {
        v.visitId(this);
    }
}
