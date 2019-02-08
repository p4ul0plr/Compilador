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

    public String tipoSimples;
    
    public NodeTipoSimples(String tipoSimples) {
        this.tipoSimples = tipoSimples;
    }

    public NodeTipoSimples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void visit(Visitor v) {
        v.visitTipoSimples(this);
    }
}
