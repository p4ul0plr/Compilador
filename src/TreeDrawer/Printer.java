/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeDrawer;

import abstractSyntaxTrees.NodeAtribuicao;
import abstractSyntaxTrees.NodeBoolLit;
import abstractSyntaxTrees.NodeComando;
import abstractSyntaxTrees.NodeComandoComposto;
import abstractSyntaxTrees.NodeCondicional;
import abstractSyntaxTrees.NodeCorpo;
import abstractSyntaxTrees.NodeDeclaracao;
import abstractSyntaxTrees.NodeDeclaracaoDeVariavel;
import abstractSyntaxTrees.NodeDeclaracoes;
import abstractSyntaxTrees.NodeExpressao;
import abstractSyntaxTrees.NodeExpressaoSimples;
import abstractSyntaxTrees.NodeExpressaoSimplesComplemento;
import abstractSyntaxTrees.NodeFator;
import abstractSyntaxTrees.NodeFloatLit;
import abstractSyntaxTrees.NodeId;
import abstractSyntaxTrees.NodeIntLit;
import abstractSyntaxTrees.NodeIterativo;
import abstractSyntaxTrees.NodeListaDeComandos;
import abstractSyntaxTrees.NodeListaDeIds;
import abstractSyntaxTrees.NodeLiteral;
import abstractSyntaxTrees.NodeOpAd;
import abstractSyntaxTrees.NodeOpMul;
import abstractSyntaxTrees.NodeOpRel;
import abstractSyntaxTrees.NodePrograma;
import abstractSyntaxTrees.NodeSeletor;
import abstractSyntaxTrees.NodeTermo;
import abstractSyntaxTrees.NodeTermoComplemento;
import abstractSyntaxTrees.NodeTipo;
import abstractSyntaxTrees.NodeTipoAgregado;
import abstractSyntaxTrees.NodeTipoSimples;
import abstractSyntaxTrees.NodeVariavel;
import abstractSyntaxTrees.Visitor;

/**
 *
 * @author paulo
 */
public class Printer implements Visitor {

    private int i = 0;
    
    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        if (nodeAtribuicao != null) {
            nodeAtribuicao.nodeVariavel.visit(this);
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitComando(NodeComando nodeComando) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitComandoComposto(NodeComandoComposto nodeComandoComposto) {
        if (nodeComandoComposto != null) {
            System.out.println("begin ");
            nodeComandoComposto.nodeListaDeComandos.visit(this);
            System.out.println("end ");
        }
    }

    @Override
    public void visitCondicional(NodeCondicional nodeCondicional) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitCorpo(NodeCorpo nodeCorpo) {
        if (nodeCorpo != null) {
            if (nodeCorpo.nodeDeclaracoes != null) {
                i++;
                indent();
                nodeCorpo.nodeDeclaracoes.visit(this);
                i--;
            }
            if (nodeCorpo.nodeComandoComposto != null) {
                i++;
                indent();
                nodeCorpo.nodeComandoComposto.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitDeclaracao(NodeDeclaracao nodeDeclaracao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitDeclaracaoDeVariavel(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitDeclaracoes(NodeDeclaracoes nodeDeclaracoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitExpressao(NodeExpressao nodeExpressao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitFator(NodeFator nodeFator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitFloatLit(NodeFloatLit nodeFloatLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitId(NodeId nodeId) {
        if (nodeId != null) {
            System.out.println(nodeId.identificador);
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitListaDeComandos(NodeListaDeComandos nodeListaDeComandos) {
        if (nodeListaDeComandos != null) {
            
        }
    }

    @Override
    public void visitListaDeIds(NodeListaDeIds nodeListaDeIds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitLiteral(NodeLiteral nodeLiteral) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitOpAd(NodeOpAd nodeOpAd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitOpMul(NodeOpMul nodeOpMul) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitOpRel(NodeOpRel nodeOpRel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitPrograma(NodePrograma nodePrograma) {
        if (nodePrograma != null) {
            System.out.println("programa ");
            if (nodePrograma.nodeId != null) {
                nodePrograma.nodeId.visit(this);
            }
            System.out.println("; ");
            if (nodePrograma.nodeCorpo != null) {
                nodePrograma.nodeCorpo.visit(this);
            }
            System.out.println(".");
        }
    }

    @Override
    public void visitSeletor(NodeSeletor nodeSeletor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitTipo(NodeTipo nodeTipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitTipoAgregado(NodeTipoAgregado nodeTipoAgregado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void print(NodePrograma nodePrograma) {
        System.out.println("---> Imprimindo a arvore");
        nodePrograma.visit(this);
    }

    public void indent() {
        for (int j = 0; j < i; j++) {
            System.out.print("|");
        }
    }

}
