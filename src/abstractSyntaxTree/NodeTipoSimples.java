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
public class NodeTipoSimples extends NodeTipo {

    public String tipoSimples;
    
    public NodeTipoSimples(String tipoSimples) {
        this.tipoSimples = tipoSimples;
    }

    @Override
    public void visit(Visitor v) {
        v.visitTipoSimples(this);
    }
}
