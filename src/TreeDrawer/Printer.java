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

    }

    @Override
    public void visitCorpo(NodeCorpo nodeCorpo) {
        //System.out.println("visitCorpo");
        if (nodeCorpo != null) {
            if (nodeCorpo.nodeDeclaracoes != null) {
                nodeCorpo.nodeDeclaracoes.visit(this);
            }
            if (nodeCorpo.nodeComandoComposto != null) {
                //nodeCorpo.nodeComandoComposto.visit(this);
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

    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {

    }

    @Override
    public void visitExpressaoSimplesComplemento(NodeExpressaoSimplesComplemento nodeExpressaoSimplesComplemento) {

    }

    @Override
    public void visitFator(NodeFator nodeFator) {

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
            System.out.print(nodeIntLit.intLiteral + " ");
        }
    }

    @Override
    public void visitIterativo(NodeIterativo nodeIterativo) {

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
                i++;
                nodeListaDeIds.next.visit(this);
                i--;
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
        //System.out.println("visitPrograma");
        if (nodePrograma != null) {
            if (nodePrograma.nodeId != null) {
                nodePrograma.nodeId.visit(this);
                System.out.println("");
            }
            if (nodePrograma.nodeCorpo != null) {
                nodePrograma.nodeCorpo.visit(this);
            }
        }
    }

    @Override
    public void visitSeletor(NodeSeletor nodeSeletor) {

    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {

    }

    @Override
    public void visitTermoComplemento(NodeTermoComplemento nodeTermoComplemento) {

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
