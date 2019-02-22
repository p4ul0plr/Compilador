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
            if (nodeAtribuicao.nodeVariavel != null) {
                nodeAtribuicao.nodeVariavel.visit(this);
            }
            System.out.println("");
            if (nodeAtribuicao.nodeExpressao != null) {
                indent();
                nodeAtribuicao.nodeExpressao.visit(this);
            }
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        if (nodeBoolLit != null) {
            System.out.print(nodeBoolLit.booleano + " ");
        }
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
        //System.out.println("visitCorpo");
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
        //System.out.println("visitDeclaracao");
        if (nodeDeclaracao != null) {
            nodeDeclaracao.nodeDeclaracaoDeVariavel.visit(this);
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
        //System.out.println("visitDeclaracoes");
        if (nodeDeclaracoes != null) {
            nodeDeclaracoes.nodeDeclaracao.visit(this);
            if (nodeDeclaracoes.next != null) {
                i++;
                indent();
                nodeDeclaracoes.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitExpressao(NodeExpressao nodeExpressao) {
        i++;
        indent();
        if (nodeExpressao != null) {
            if (nodeExpressao.nodeOpRel != null) {
                nodeExpressao.nodeOpRel.visit(this);
            }
            if (nodeExpressao.nodeExpressaoSimples1 != null) {
                nodeExpressao.nodeExpressaoSimples1.visit(this);
            }
            if (nodeExpressao.nodeExpressaoSimples2 != null) {
                nodeExpressao.nodeExpressaoSimples2.visit(this);
            }
            System.out.println("");
        }
        i--;
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        if (nodeExpressaoSimples != null) {
            if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento != null) {
                nodeExpressaoSimples.nodeExpressaoSimplesComplemento.visit(this);
            }
            if (nodeExpressaoSimples.nodeTermo != null) {
                nodeExpressaoSimples.nodeTermo.visit(this);
            }
        }
    }

    @Override
    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {
        if (nodeExpressaoSimplesComplemento != null) {
            if (nodeExpressaoSimplesComplemento.nodeTermo != null) {
                nodeExpressaoSimplesComplemento.nodeTermo.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.nodeOpAd != null) {
                nodeExpressaoSimplesComplemento.nodeOpAd.visit(this);
            }
            if (nodeExpressaoSimplesComplemento.next != null) {
                i++;
                indent();
                nodeExpressaoSimplesComplemento.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitFator(NodeFator nodeFator) {
        if (nodeFator != null) {
            if (nodeFator != null) {
                nodeFator.visit(this);
            }
        }
    }

    @Override
    public void visitFloatLit(NodeFloatLit nodeFloatLit) {
        if (nodeFloatLit != null) {
            System.out.println(nodeFloatLit.floatLiteral + " ");
        }
    }

    @Override
    public void visitId(NodeId nodeId) {
        //System.out.println("visitId");
        if (nodeId != null) {
            System.out.print(nodeId.identificador);
        }
    }

    @Override
    public void visitIntLit(NodeIntLit nodeIntLit) {
        if (nodeIntLit != null) {
            //i++;
            //indent();
            System.out.print(nodeIntLit.intLiteral + " ");
            //i--;
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
            nodeListaDeComandos.nodeComando.visit(this);
            if (nodeListaDeComandos.next != null) {
                i++;
                indent();
                nodeListaDeComandos.next.visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitListaDeIds(NodeListaDeIds nodeListaDeIds) {
        //System.out.println("visitListaDeIds");
        if (nodeListaDeIds != null) {
            if (nodeListaDeIds.nodeId != null) {
                System.out.print(nodeListaDeIds.nodeId.identificador + " ");
            }
            if (nodeListaDeIds.next != null) {
                //i++;
                nodeListaDeIds.next.visit(this);
                //i--;
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
        if (nodeOpAd != null) {
            i++;
            indent();
            System.out.println(nodeOpAd.opAd);
            i--;
        }
    }

    @Override
    public void visitOpMul(NodeOpMul nodeOpMul) {
        if (nodeOpMul != null) {
            i++;
            indent();
            System.out.println(nodeOpMul.opMul);
            i--;
        }
    }

    @Override
    public void visitOpRel(NodeOpRel nodeOpRel) {
        if (nodeOpRel != null) {
            i++;
            indent();
            System.out.println(nodeOpRel.opRel);
            i--;
        }
    }

    @Override
    public void visitPrograma(NodePrograma nodePrograma) {
        //System.out.println("visitPrograma");
        if (nodePrograma != null) {
            if (nodePrograma.nodeId != null) {
                //nodePrograma.nodeId.visit(this);
                System.out.println("");
            }
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
            if (nodeTermo.nodeTermoComplemento != null) {
                nodeTermo.nodeTermoComplemento.visit(this);
            }
            if (nodeTermo.nodeFator != null) {
                nodeTermo.nodeFator.visit(this);
            }
        }
    }

    @Override
    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {
        if (nodeTermoComplemento != null) {
            if (nodeTermoComplemento.nodeFator != null) {
                nodeTermoComplemento.nodeFator.visit(this);
            }
            if (nodeTermoComplemento.nodeOpMul != null) {
                nodeTermoComplemento.nodeOpMul.visit(this);
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
        if (nodeTipoAgregado != null) {
            if (nodeTipoAgregado.nodeLiteral1 != null) {
                nodeTipoAgregado.nodeLiteral1.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral2 != null) {
                nodeTipoAgregado.nodeLiteral2.visit(this);
            }
            if (nodeTipoAgregado.nodeTipo != null) {
                nodeTipoAgregado.nodeTipo.visit(this);
            }
        }
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        if (nodeTipoSimples != null) {
            System.out.println(nodeTipoSimples.tipoSimples);
        }
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        i++;
        indent();
        if (nodeVariavel != null) {
            if (nodeVariavel.nodeId != null) {
                nodeVariavel.nodeId.visit(this);
            }
            if (nodeVariavel.nodeSeletor != null) {
                nodeVariavel.nodeSeletor.visit(this);
            }
        }
        System.out.println("");
        i--;
    }

    public void print(NodePrograma nodePrograma) {
        System.out.println("---> Imprimindo a arvore");
        nodePrograma.visit(this);
    }

    public void indent() {
        for (int j = 0; j < i; j++) {
            System.out.print("|   ");
        }
    }

}