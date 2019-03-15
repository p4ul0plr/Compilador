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
public class NodeExpressaoSimplesComplemento {
    
    public NodeOpAd nodeOpAd;
    public NodeTermo nodeTermo;
    public NodeExpressaoSimplesComplemento next;
    public byte kind = -2;  //-2 é que está vazio
    
    public void visit(Visitor v) {
        v.visitExpressaoSimplesComplemento(this);
    }
}
