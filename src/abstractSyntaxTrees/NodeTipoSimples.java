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
public class NodeTipoSimples extends NodeTipo {

    public char tipoSimples;
    
    public NodeTipoSimples(char tipoSimples) {
        this.tipoSimples = tipoSimples;
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitTipoSimples(this);
    }
}
