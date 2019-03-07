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
public class NodeBoolLit extends NodeLiteral {

    public String booleano;

    public NodeBoolLit(String booleano) {
        this.booleano = booleano;
    }

    @Override
    public void visit(Visitor v) {
        v.visitBoolLit(this);
    }
}