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
public class NodeListaDeIds {

    public NodeId nodeId;
    public NodeListaDeIds next;
    
    public NodeListaDeIds(NodeId nodeId, NodeListaDeIds next) {
        this.nodeId = nodeId;
        this.next = next;
    }

    public void visit(Visitor v) {
        v.visitListaDeIds(this);
    }
}