/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractSyntaxTrees;

/**
 *
 * @author paulo
 */
public class NodeVariavel extends NodeFator {

    public NodeId nodeId;
    public NodeSeletor nodeSeletor;
    
    public NodeVariavel(NodeId nodeId, NodeSeletor nodeSeletor) {
        this.nodeId = nodeId;
        this.nodeSeletor = nodeSeletor;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitVariavel(this);
    }
}
