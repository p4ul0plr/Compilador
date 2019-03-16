/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalysis;

import abstractSyntaxTree.NodeId;
import abstractSyntaxTree.NodeTipo;

/**
 *
 * @author paulo
 */
public class Node {

    private NodeId nodeId;
    private NodeTipo nodeTipo;

    public NodeId getNodeId() {
        return nodeId;
    }

    public void setNodeId(NodeId nodeId) {
        this.nodeId = nodeId;
    }

    public NodeTipo getNodeTipo() {
        return nodeTipo;
    }

    public void setNodeTipo(NodeTipo nodeTipo) {
        this.nodeTipo = nodeTipo;
    }
}
