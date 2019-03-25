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
public class NodeIntLit extends NodeLiteral {

    public String intLiteral;

    public NodeIntLit(String intLiteral) {
        this.intLiteral = intLiteral;
    }

    public NodeIntLit(byte kind, String intLiteral, int line, int column) {
        this.kind = kind;
        this.intLiteral = intLiteral;
        this.line = line;
        this.column = column;
    }

    @Override
    public void visit(Visitor v) {
        v.visitIntLit(this);
    }

    @Override
    public byte getKind() {
        return kind;
    }

    @Override
    public void setKind(byte kind) {
        this.kind = kind;
    }

    public String getIntLiteral() {
        return intLiteral;
    }

    public void setIntLiteral(String intLiteral) {
        this.intLiteral = intLiteral;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setColumn(int column) {
        this.column = column;
    }

}
