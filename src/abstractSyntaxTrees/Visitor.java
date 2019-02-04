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
public interface Visitor {
    public void visitAtribuicao (NodeAtribuicao nodeAtribuicao);
    public void visitBoolLit (NodeBoolLit nodeBoolLit);
    //public void visitComando (NodeComando nodeComando); //É abstrata
    public void visitComandoComposto (NodeComandoComposto nodeComandoComposto); 
    public void visitCondicional (NodeCondicional nodeCondicional);
    public void visitCorpo (NodeCorpo nodeCorpo);
    public void visitDeclaracao (NodeDeclaracao nodeDeclaracao);
    public void visitDeclaracaoDeVariavel (NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel);
    public void visitDeclaracoes (NodeDeclaracoes nodeDeclaracoes);
    public void visitExpressao (NodeExpressao nodeExpressao);
    public void visitExpressaoSimples (NodeExpressaoSimples nodeExpressaoSimples);
    

}
