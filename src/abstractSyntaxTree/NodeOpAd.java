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
public class NodeOpAd {

    public StringBuffer opAd;
    
    public NodeOpAd(StringBuffer opAd) {
        this.opAd = opAd;
    }

    public void visit(Visitor v) {
        v.visitOpAd(this);
    }
}