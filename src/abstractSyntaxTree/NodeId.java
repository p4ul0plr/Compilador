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
public class NodeId extends Token {
    
    /*public String identificador;
    
    public NodeId(String identificador) {
        this.identificador = identificador;
    }*/
    private int dimensao;
    
    public NodeId(byte kind, String spelling, int line, int column) {
        super(kind, spelling, line, column);
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public byte getKind() {
        return kind;
    }

    @Override
    public void setKind(byte kind) {
        this.kind = kind;
    }

    @Override
    public String getSpelling() {
        return spelling;
    }

    @Override
    public void setSpelling(String spelling) {
        this.spelling = spelling;
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
    
    

    public void visit(Visitor v) {
        v.visitId(this);
    }
}
