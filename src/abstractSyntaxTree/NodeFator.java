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
public abstract class NodeFator {
    
    public byte kind;

    public abstract void visit(Visitor v);
}
