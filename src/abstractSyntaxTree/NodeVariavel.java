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
public class NodeVariavel extends NodeFator {

    public NodeId nodeId;
    public NodeSeletor nodeSeletor;
    private int dimensao;

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitVariavel(this);
    }
}
