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
public class NodeTermo {

    public NodeFator nodeFator;
    public NodeTermoComplemento nodeTermoComplemento;
    public byte kind;
    public int line;
    public int column;

    public NodeFator getNodeFator() {
        return nodeFator;
    }

    public void setNodeFator(NodeFator nodeFator) {
        this.nodeFator = nodeFator;
    }

    public NodeTermoComplemento getNodeTermoComplemento() {
        return nodeTermoComplemento;
    }

    public void setNodeTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {
        this.nodeTermoComplemento = nodeTermoComplemento;
    }

    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public void visit(Visitor v) {
        v.visitTermo(this);
    }
}
