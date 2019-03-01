/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextAnalysis;

import abstractSyntaxTree.NodeAtribuicao;
import abstractSyntaxTree.NodeBoolLit;
import abstractSyntaxTree.NodeComando;
import abstractSyntaxTree.NodeComandoComposto;
import abstractSyntaxTree.NodeCondicional;
import abstractSyntaxTree.NodeCorpo;
import abstractSyntaxTree.NodeDeclaracao;
import abstractSyntaxTree.NodeDeclaracaoDeVariavel;
import abstractSyntaxTree.NodeDeclaracoes;
import abstractSyntaxTree.NodeExpressao;
import abstractSyntaxTree.NodeExpressaoSimples;
import abstractSyntaxTree.NodeExpressaoSimplesComplemento;
import abstractSyntaxTree.NodeFator;
import abstractSyntaxTree.NodeFloatLit;
import abstractSyntaxTree.NodeId;
import abstractSyntaxTree.NodeIntLit;
import abstractSyntaxTree.NodeIterativo;
import abstractSyntaxTree.NodeListaDeComandos;
import abstractSyntaxTree.NodeListaDeIds;
import abstractSyntaxTree.NodeLiteral;
import abstractSyntaxTree.NodeOpAd;
import abstractSyntaxTree.NodeOpMul;
import abstractSyntaxTree.NodeOpRel;
import abstractSyntaxTree.NodePrograma;
import abstractSyntaxTree.NodeSeletor;
import abstractSyntaxTree.NodeTermo;
import abstractSyntaxTree.NodeTermoComplemento;
import abstractSyntaxTree.NodeTipo;
import abstractSyntaxTree.NodeTipoAgregado;
import abstractSyntaxTree.NodeTipoSimples;
import abstractSyntaxTree.NodeVariavel;
import abstractSyntaxTree.Visitor;

/**
 *
 * @author paulo
 */
public class Checker implements Visitor {

    private final IdentificationTable t = new IdentificationTable();
    private boolean fechaDeclaracoes = false;

    public void Check(NodePrograma nodePrograma) {
        System.out.println("---> Iniciando identificacao de nomes\n");
        nodePrograma.visit(this);
    }

    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        if (nodeAtribuicao != null) {
            if (nodeAtribuicao.nodeVariavel != null) {
                nodeAtribuicao.nodeVariavel.visit(this);
            }
            if (nodeAtribuicao.nodeExpressao != null) {

            }
        }
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
            fechaDeclaracoes = true;
            nodeComandoComposto.nodeListaDeComandos.visit(this);
        }
    }

    @Override
    public void visitCondicional(NodeCondicional nodeCondicional) {
        if (nodeCondicional != null) {

        }
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
            if (fechaDeclaracoes) {
                t.retrieve(nodeId.identificador);
            } else {
                t.enter(nodeId.identificador);
            }
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {
        if (nodeIterativo != null) {

        }
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
            if (nodeListaDeIds.next != null) {
                nodeListaDeIds.next.visit(this);
            }
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
        if (nodeTipo != null) {
            nodeTipo.visit(this);
        }
    }

    @Override
    public void visitTipoAgregado(NodeTipoAgregado nodeTipoAgregado) {
        if (nodeTipoAgregado != null) {

        }
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        if (nodeTipoSimples != null) {

        }
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        if (nodeVariavel != null) {
            if (nodeVariavel.nodeId != null) {
                nodeVariavel.nodeId.visit(this);
            }
            if (nodeVariavel.nodeSeletor != null) {
                //nodeVariavel.nodeSeletor.visit(this);
            }
        }
    }

}
