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
public class NodeFloatLit extends NodeLiteral {

    public char floatLiteral;

    public NodeFloatLit(char floatLiteral) {
        this.floatLiteral = floatLiteral;
    }

    @Override
    public void visit(Visitor v) {
        v.visitFloatLit(this);
    }
}
