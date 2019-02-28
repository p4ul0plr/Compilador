/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

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
public class Checker implements Visitor {

    IdentificationTable t = new IdentificationTable();

    public void Check(NodePrograma nodePrograma) {
        System.out.println("---> Iniciando identificacao de nomes");
        nodePrograma.visit(this);
    }

    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitComando(NodeComando nodeComando) {
        if (nodeComando != null) {
            nodeComando.visit(this);
        }
    }

    @Override
    public void visitComandoComposto(NodeComandoComposto nodeComandoComposto) {
        if (nodeComandoComposto != null) {
            nodeComandoComposto.nodeListaDeComandos.visit(this);
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
                nodeCorpo.nodeDeclaracoes.visit(this);
            }
            if (nodeCorpo.nodeComandoComposto != null) {
                nodeCorpo.nodeComandoComposto.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracao(NodeDeclaracao nodeDeclaracao) {
        if (nodeDeclaracao != null) {
            if (nodeDeclaracao.nodeDeclaracaoDeVariavel != null) {
                nodeDeclaracao.nodeDeclaracaoDeVariavel.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracaoDeVariavel(NodeDeclaracaoDeVariavel nodeDeclaracaoDeVariavel) {
        if (nodeDeclaracaoDeVariavel != null) {
            if (nodeDeclaracaoDeVariavel.nodeListaDeIds != null) {
                nodeDeclaracaoDeVariavel.nodeListaDeIds.visit(this);
            }
            if (nodeDeclaracaoDeVariavel.nodeTipo != null) {
                nodeDeclaracaoDeVariavel.nodeTipo.visit(this);
            }
        }
    }

    @Override
    public void visitDeclaracoes(NodeDeclaracoes nodeDeclaracoes) {
        if (nodeDeclaracoes != null) {
            if (nodeDeclaracoes.nodeDeclaracao != null) {
                nodeDeclaracoes.nodeDeclaracao.visit(this);
            }
            if (nodeDeclaracoes.next != null) {
                nodeDeclaracoes.next.visit(this);
            }
        }
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
            t.enter(nodeId.identificador);
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
            if (nodeListaDeComandos.nodeComando != null) {
                nodeListaDeComandos.nodeComando.visit(this);
            }
            if (nodeListaDeComandos.next != null) {
                nodeListaDeComandos.next.visit(this);
            }
        }
    }

    @Override
    public void visitListaDeIds(NodeListaDeIds nodeListaDeIds) {
        if (nodeListaDeIds != null) {
            if (nodeListaDeIds.nodeId != null) {
                nodeListaDeIds.nodeId.visit(this);
            }
        }
        if (nodeListaDeIds.next != null) {
            nodeListaDeIds.next.visit(this);
        }
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
            if (nodePrograma.nodeCorpo != null) {
                nodePrograma.nodeCorpo.visit(this);
            }
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

}
