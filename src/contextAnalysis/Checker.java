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
import lexicalAnalysis.Token;

/**
 *
 * @author paulo
 */
public class Checker implements Visitor {

    private final IdentificationTable t = new IdentificationTable();
    private boolean fechaDeclaracoes = false;
    //private boolean ehTipoAgregado = false;
    //private String indiceMenor = null, indiceMaior = null;

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
                nodeAtribuicao.nodeExpressao.visit(this);
            }
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {

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
            if (nodeComandoComposto.nodeListaDeComandos != null) {
                nodeComandoComposto.nodeListaDeComandos.visit(this);
            }
        }
    }

    @Override
    public void visitCondicional(NodeCondicional nodeCondicional) {
        if (nodeCondicional != null) {
            if (nodeCondicional.nodeExpressao != null) {
                nodeCondicional.nodeExpressao.visit(this);
            }
            if (nodeCondicional.nodeComandoIf != null) {
                nodeCondicional.nodeComandoIf.visit(this);
            }
            if (nodeCondicional.nodeComandoElse != null) {
                nodeCondicional.nodeComandoElse.visit(this);
            }
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
        if (nodeExpressao != null) {
            if (nodeExpressao.nodeExpressaoSimples1 != null) {
                nodeExpressao.nodeExpressaoSimples1.visit(this);
            }
            if (nodeExpressao.nodeOpRel != null) {
                nodeExpressao.nodeOpRel.visit(this);
            }
            if (nodeExpressao.nodeExpressaoSimples2 != null) {
                nodeExpressao.nodeExpressaoSimples2.visit(this);
            }
        }
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        if (nodeExpressaoSimples != null) {
            if (nodeExpressaoSimples.nodeTermo != null) {
                nodeExpressaoSimples.nodeTermo.visit(this);
            }
            if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento != null) {
                nodeExpressaoSimples.nodeExpressaoSimplesComplemento.visit(this);
            }
        }
    }

    @Override
    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        if (nodeExpressaoSimplesComplemento != null) {
            if (nodeExpressaoSimplesComplemento.nodeOpAd != null) {
                nodeExpressaoSimplesComplemento.nodeOpAd.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.nodeTermo != null) {
                nodeExpressaoSimplesComplemento.nodeTermo.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.next != null) {
                nodeExpressaoSimplesComplemento.next.visit(this);
            }
        }
    }

    @Override
    public void visitFator(NodeFator nodeFator) {
        if (nodeFator != null) {
            nodeFator.visit(this);
        }
    }

    @Override
    public void visitFloatLit(NodeFloatLit nodeFloatLit) {

    }

    @Override
    public void visitId(NodeId nodeId) {
        if (nodeId != null) {
            if (fechaDeclaracoes) {
                t.retrieve(new Token(Token.ID, nodeId.spelling, nodeId.line, nodeId.column));
            } else {
                t.enter(new Token(Token.ID, nodeId.spelling, nodeId.line, nodeId.column));
            }
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        if (nodeIntLit != null) {
            /*if (ehTipoAgregado) {
                if (indiceMenor == null) {
                    indiceMenor = nodeIntLit.intLiteral;
                } else if(indiceMaior == null) {
                    indiceMaior = nodeIntLit.intLiteral;
                } else {
                    indiceMenor = nodeIntLit.intLiteral;
                    indiceMaior = null;
                }
            }*/
        }
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {
        if (nodeIterativo != null) {
            if (nodeIterativo.nodeExpressao != null) {
                nodeIterativo.nodeExpressao.visit(this);
            }
            if (nodeIterativo.nodeComando != null) {
                nodeIterativo.nodeComando.visit(this);
            }
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
        if (nodeLiteral != null) {
            nodeLiteral.visit(this);
        }
    }

    @Override
    public void visitOpAd(NodeOpAd nodeOpAd) {

    }

    @Override
    public void visitOpMul(NodeOpMul nodeOpMul) {

    }

    @Override
    public void visitOpRel(NodeOpRel nodeOpRel) {

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
        if (nodeSeletor != null) {
            if (nodeSeletor.nodeExpressao != null) {
                nodeSeletor.nodeExpressao.visit(this);
            }
            if (nodeSeletor.next != null) {
                nodeSeletor.next.visit(this);
            }
        }
    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {
        if (nodeTermo != null) {
            if (nodeTermo.nodeFator != null) {
                nodeTermo.nodeFator.visit(this);
            }
            if (nodeTermo.nodeTermoComplemento != null) {
                nodeTermo.nodeTermoComplemento.visit(this);
            }
        }
    }

    @Override
    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {
        if (nodeTermoComplemento != null) {
            if (nodeTermoComplemento.nodeOpMul != null) {
                nodeTermoComplemento.nodeOpMul.visit(this);
            }
            if (nodeTermoComplemento.nodeFator != null) {
                nodeTermoComplemento.nodeFator.visit(this);
            }
            if (nodeTermoComplemento.next != null) {
                nodeTermoComplemento.next.visit(this);
            }
        }
    }

    @Override
    public void visitTipo(NodeTipo nodeTipo) {
        if (nodeTipo != null) {
            nodeTipo.visit(this);
        }
    }

    @Override
    public void visitTipoAgregado(NodeTipoAgregado nodeTipoAgregado) {
        NodeIntLit indiceMenorS = null, indiceMaiorS;
        int indiceMenorI = 0, indiceMaiorI = 0;
        if (nodeTipoAgregado != null) {
            //ehTipoAgregado = true;
            if (nodeTipoAgregado.nodeTipo != null) {
                nodeTipoAgregado.nodeTipo.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral1 != null) {
                if (nodeTipoAgregado.nodeLiteral1 instanceof NodeIntLit) {
                    indiceMenorS = ((NodeIntLit)nodeTipoAgregado.nodeLiteral1);
                    indiceMenorI = Integer.parseInt(indiceMenorS.getIntLiteral());
                } else {
                    System.out.println("CONTEXT ERROR! - Index 1 invalid - An index of type <int-lit> was expected.");
                    System.exit(0);
                }
            }
            if (nodeTipoAgregado.nodeLiteral2 != null) {
                if (nodeTipoAgregado.nodeLiteral2 instanceof NodeIntLit) {
                    indiceMaiorS = ((NodeIntLit)nodeTipoAgregado.nodeLiteral2);
                    indiceMaiorI = Integer.parseInt(indiceMaiorS.getIntLiteral());
                } else {
                    System.out.println("CONTEXT ERROR! - Index 2 invalid - An index of type <int-lit> was expected.");
                    System.exit(0);
                }
            }
            if (indiceMenorI >= indiceMaiorI) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + indiceMenorS.getLine()
                        + " COLUMN: " + indiceMenorS.getColumn()
                        + " Index \"" + indiceMenorI + "\""
                        + " should be lower than index \"" + indiceMaiorI + "\".");
            }
            //System.out.println("indiceMenor: " + indiceMenor + "\nindiceMaior: " + indiceMaior);
            //ehTipoAgregado = false;
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
                nodeVariavel.nodeSeletor.visit(this);
            }
        }
    }

}
