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
public class NodeFloatLit extends NodeLiteral {

    public String floatLiteral;
    public int line;
    public int column;

    public NodeFloatLit(String floatLiteral) {
        this.floatLiteral = floatLiteral;
    }

    public NodeFloatLit(byte kind, String floatLiteral, int line, int column) {
        this.kind = kind;
        this.floatLiteral = floatLiteral;
        this.line = line;
        this.column = column;
    }

    @Override
    public void visit(Visitor v) {
        v.visitFloatLit(this);
    }
}
