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
public class NodePrograma extends AST {
    
    public NodeId nodeId;
    public NodeCorpo nodeCorpo;
    
    public NodePrograma(NodeId nodeId, NodeCorpo nodeCorpo) {
        this.nodeId = nodeId;
        this.nodeCorpo = nodeCorpo;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitPrograma(this);
    }
}
