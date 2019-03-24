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
public abstract class NodeTipo {

    public byte kind;
    private int dimensao;

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }
    
    public abstract void visit(Visitor v);
}
