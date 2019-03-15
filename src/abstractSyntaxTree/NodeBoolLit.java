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
public class NodeBoolLit extends NodeLiteral {

    public String booleano;
    public int line;
    public int column;

    public NodeBoolLit(String booleano) {
        this.booleano = booleano;
    }

    public NodeBoolLit(byte kind, String booleano, int line, int column) {
        this.kind = kind;
        this.booleano = booleano;
        this.line = line;
        this.column = column;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitBoolLit(this);
    }
}
