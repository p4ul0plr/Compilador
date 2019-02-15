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
public class NodeOpAd {

    public String opAd;
    
    public NodeOpAd(String opAd) {
        this.opAd = opAd;
    }

    public void visit(Visitor v) {
        v.visitOpAd(this);
    }
}
