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
    private byte tA;
    private int dimensãoVariavel = 0;
    private int quantidadeSeletor = 0;

    //private boolean fechaDeclaracoes = false;
    //private boolean ehTipoAgregado = false;
    //private String indiceMenor = null, indiceMaior = null;

    public void Check(NodePrograma nodePrograma) {
        System.out.println("---> Iniciando identificacao de nomes\n");
        nodePrograma.visit(this);
    }

    @Override
    public void visitAtribuicao(NodeAtribuicao nodeAtribuicao) {
        if (nodeAtribuicao != null) {
            byte v = -1, e = -1;
            int line = 0, column = 0;
            if (nodeAtribuicao.nodeVariavel != null) {
                nodeAtribuicao.nodeVariavel.visit(this);
                v = nodeAtribuicao.nodeVariavel.kind;
                line = nodeAtribuicao.nodeVariavel.nodeId.line;
                column = nodeAtribuicao.nodeVariavel.nodeId.column; //Problema na contagem de colunas
            }
            if (nodeAtribuicao.nodeExpressao != null) {
                nodeAtribuicao.nodeExpressao.visit(this);
                e = nodeAtribuicao.nodeExpressao.kind;
            }
            if (v == e && v != -1 && e != -1) {
                nodeAtribuicao.kind = v;
            } else if (v == 3 && e == 1 || v == 4 && e == 2) {
                nodeAtribuicao.kind = v;
            } else {
                nodeAtribuicao.kind = -1;
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + line
                        + " COLUMN: " + column
                        + " - Assignment with incompatible types"
                        + " - The variable \"" + nodeAtribuicao.nodeVariavel.nodeId.getSpelling()
                        + "\" of type \"" + Token.spellings[v]
                        + "\" is not compatible with type \"" + Token.spellings[e] + "\".");
                System.exit(0);
            }
        }
    }

    @Override
    public void visitBoolLit(NodeBoolLit nodeBoolLit) {
        if (nodeBoolLit != null) {
            //System.out.println(nodeBoolLit.kind);
            //nodeBoolLit.kind;
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
            t.imprime();
            //fechaDeclaracoes = true;
            if (nodeComandoComposto.nodeListaDeComandos != null) {
                nodeComandoComposto.nodeListaDeComandos.visit(this);
            }
        }
    }

    @Override
    public void visitCondicional(NodeCondicional nodeCondicional) {
        if (nodeCondicional != null) {
            byte c = -1;
            int line = 0, column = 0;
            if (nodeCondicional.nodeExpressao != null) {
                nodeCondicional.nodeExpressao.visit(this);
                c = nodeCondicional.nodeExpressao.kind;
                if (c != 5) {
                    System.out.println("CONTEXT ERROR! -"
                            //+ " LINE: " + line
                            //+ " COLUMN: " + column 
                            + " Assignment with incompatible types");
                    System.exit(0);
                }
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
                if (nodeDeclaracaoDeVariavel.nodeTipo instanceof NodeTipoAgregado) {
                    nodeDeclaracaoDeVariavel.nodeTipo.kind = tA;
                }
            }
            nodeDeclaracaoDeVariavel.nodeListaDeIds.setDimensao(dimensãoVariavel);
            //System.out.println(nodeDeclaracaoDeVariavel.nodeListaDeIds.getDimensao());
            dimensãoVariavel = 0;
            t.enter(nodeDeclaracaoDeVariavel);
            //t.imprime();

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
            byte es1 = -1, es2 = -1, opRel;
            if (nodeExpressao.nodeExpressaoSimples1 != null) {
                nodeExpressao.nodeExpressaoSimples1.visit(this);
                es1 = nodeExpressao.nodeExpressaoSimples1.kind;
            }
            if (nodeExpressao.nodeOpRel != null) {
                nodeExpressao.nodeOpRel.visit(this);
                opRel = nodeExpressao.nodeOpRel.getKind();
            }
            if (nodeExpressao.nodeExpressaoSimples2 != null) {
                nodeExpressao.nodeExpressaoSimples2.visit(this);
                es2 = nodeExpressao.nodeExpressaoSimples2.kind;
                if (es1 == es2 && es1 != -1 && es2 != -1 && es1 != 5 && es2 != 5) {
                    nodeExpressao.kind = 5;
                } else if (es1 == 3 && es2 == 1 || es1 == 4 && es2 == 2 || es1 == 1 && es2 == 3 || es1 == 2 && es2 == 4) {
                    nodeExpressao.kind = 5;
                } else {
                    nodeExpressao.kind = -1;
                }
            } else {
                nodeExpressao.kind = es1;
            }
        }
    }

    @Override
    public void visitExpressaoSimples(NodeExpressaoSimples nodeExpressaoSimples) {
        if (nodeExpressaoSimples != null) {
            byte t = -1, esc = -1;
            if (nodeExpressaoSimples.nodeTermo != null) {
                nodeExpressaoSimples.nodeTermo.visit(this);
                t = nodeExpressaoSimples.nodeTermo.kind;
            }
            if (nodeExpressaoSimples.nodeExpressaoSimplesComplemento != null) {
                nodeExpressaoSimples.nodeExpressaoSimplesComplemento.visit(this);
                NodeExpressaoSimplesComplemento e = nodeExpressaoSimples.nodeExpressaoSimplesComplemento;
                byte escAux;
                esc = e.nodeTermo.kind;
                do {
                    escAux = e.nodeTermo.kind;
                    if (e.nodeOpAd.getKind() == 19) { //Esse trecho limita a operação OR apenas para operandos boolean
                        if (!(esc == 5 && escAux == 5)) {
                            esc = -1;
                        }                             //O trecho termina aqui                  
                    } else if (esc != escAux && !(esc == 3 && escAux == 1 || esc == 4 && escAux == 2 || esc == 1 && escAux == 3 || esc == 2 && escAux == 4)) {
                        esc = -1;
                    }
                    e = e.next;
                } while (e != null);
                //esc = nodeExpressaoSimples.nodeTermo.kind;
                if (t == esc && esc != -1 && esc != -1 && t != 5 && esc != 5) {
                    nodeExpressaoSimples.kind = t;
                } else if (t == 3 && esc == 1 || t == 4 && esc == 2 || t == 1 && esc == 3 || t == 2 && esc == 4) {
                    nodeExpressaoSimples.kind = t;
                } else {
                    nodeExpressaoSimples.kind = -1;
                }
            } else {
                nodeExpressaoSimples.kind = t;
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
//                if (nodeExpressaoSimplesComplemento.kind == -2) {
//                    nodeExpressaoSimplesComplemento.kind = nodeExpressaoSimplesComplemento.nodeTermo.kind;
//                } else if (nodeExpressaoSimplesComplemento.kind != nodeExpressaoSimplesComplemento.nodeTermo.kind) {
//                    nodeExpressaoSimplesComplemento.kind = -1;
//                }

            }
            if (nodeExpressaoSimplesComplemento.next != null) {
                nodeExpressaoSimplesComplemento.next.visit(this);
            }
            //System.out.println(nodeExpressaoSimplesComplemento.kind);
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
        if (nodeFloatLit != null) {

        }

    }

    @Override
    public void visitId(NodeId nodeId) {
        if (nodeId != null) {
//            System.out.println("ID - id: " + nodeId.getSpelling() 
//                    + " line: " + nodeId.getLine()
//                    + " column: " + nodeId.getColumn()
//                    + " tipo: " + nodeId.getKind()
//                    + " dimensao: " + nodeId.getDimensao());
//            if (fechaDeclaracoes) {
//                t.retrieve(new Token(Token.ID, nodeId.spelling, nodeId.line, nodeId.column));
//            } else {
//                t.enter(new Token(Token.ID, nodeId.spelling, nodeId.line, nodeId.column));
//            }
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
            byte i = -1;
            int line = 0, column = 0;
            if (nodeIterativo.nodeExpressao != null) {
                nodeIterativo.nodeExpressao.visit(this);
                i = nodeIterativo.nodeExpressao.kind;
                if (i != 5) {
                    System.out.println("CONTEXT ERROR! -"
                            //+ " LINE: " + line
                            //+ " COLUMN: " + column 
                            + " Assignment with incompatible types");
                    System.exit(0);
                }
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
                //t.enter(nodeListaDeIds.nodeId);
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
            byte s = -1;
            int line = 0, column = 0;
            if (nodeSeletor.nodeExpressao != null) {
                nodeSeletor.nodeExpressao.visit(this);
                s = nodeSeletor.nodeExpressao.kind;
                if (s != 1 && s != 3) {
                    System.out.println("CONTEXT ERROR! -"
                            //+ " LINE: " + line
                            //+ " COLUMN: " + column 
                            + " The index must be \"integer\" or \"<int-lit>\".");
                    System.exit(0);
                }
            }
            quantidadeSeletor++;
            if (nodeSeletor.next != null) {
                nodeSeletor.next.visit(this);
            }
        }
    }

    @Override
    public void visitTermo(NodeTermo nodeTermo) {
        if (nodeTermo != null) {
            byte f = -1, tc = -1;
            if (nodeTermo.nodeFator != null) {
                nodeTermo.nodeFator.visit(this);
                f = nodeTermo.nodeFator.kind;
            }
            if (nodeTermo.nodeTermoComplemento != null) {
                nodeTermo.nodeTermoComplemento.visit(this);
                NodeTermoComplemento t = nodeTermo.nodeTermoComplemento;
                byte tcAux;
                tc = t.nodeFator.kind;
                do {
                    tcAux = t.nodeFator.kind;
                    if (t.nodeOpMul.getKind() == 20) { //Esse trecho limita a operação AND apenas para operandos boolean
                        if (!(tc == 5 && tcAux == 5)) {
                            tc = -1;
                        }                             //O trecho termina aqui                  
                    } else if (tc != tcAux && !(tc == 3 && tcAux == 1 || tc == 4 && tcAux == 2 || tc == 1 && tcAux == 3 || tc == 2 && tcAux == 4)) {
                        tc = -1;
                    }

                    t = t.next;
                } while (t != null);
                if (f == tc && f != -1 && tc != -1 && f != 5 && tc != 5) {
                    nodeTermo.kind = f;
                } else if (f == 3 && tc == 1 || f == 4 && tc == 2 || f == 1 && tc == 3 || f == 2 && tc == 4) {
                    nodeTermo.kind = f;
                } else {
                    nodeTermo.kind = -1;
                }
            } else {
                nodeTermo.kind = f;
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
//                if (nodeTermoComplemento.kind == -2) {
//                    nodeTermoComplemento.kind = nodeTermoComplemento.nodeFator.kind;
//                } else if (nodeTermoComplemento.kind != nodeTermoComplemento.nodeFator.kind) {
//                    nodeTermoComplemento.kind = -1;
//                }
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
            dimensãoVariavel++;
            NodeIntLit indiceMenorS = null, indiceMaiorS;
            int indiceMenorI = 0, indiceMaiorI = 0;
            //ehTipoAgregado = true;
            if (nodeTipoAgregado.nodeTipo != null) {
                nodeTipoAgregado.nodeTipo.visit(this);
            }
            if (nodeTipoAgregado.nodeLiteral1 != null) {
//                if (nodeTipoAgregado.nodeLiteral1 instanceof NodeIntLit) {
//                    indiceMenorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral1);
//                    indiceMenorI = Integer.parseInt(indiceMenorS.getIntLiteral());
//                } else {
//                    System.out.println("CONTEXT ERROR! - Index 1 invalid - An index of type <int-lit> was expected.");
//                    System.exit(0);
//                }
                indiceMenorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral1);
                indiceMenorI = Integer.parseInt(indiceMenorS.getIntLiteral());

            }
            if (nodeTipoAgregado.nodeLiteral2 != null) {
//                if (nodeTipoAgregado.nodeLiteral2 instanceof NodeIntLit) {
//                    indiceMaiorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral2);
//                    indiceMaiorI = Integer.parseInt(indiceMaiorS.getIntLiteral());
//                } else {
//                    //indiceMaiorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral2);
//                    System.out.println("CONTEXT ERROR! - "
//                            //+ " LINE: " + indiceMaiorS.getLine()
//                            //+ " COLUMN: " + indiceMaiorS.getColumn()
//                            + " - Index 2 invalid - An index of type <int-lit> was expected.");
//                    System.exit(0);
//                }
                indiceMaiorS = ((NodeIntLit) nodeTipoAgregado.nodeLiteral2);
                indiceMaiorI = Integer.parseInt(indiceMaiorS.getIntLiteral());
            }
            if (indiceMenorI >= indiceMaiorI) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + indiceMenorS.getLine()
                        + " COLUMN: " + indiceMenorS.getColumn()
                        + " - Index \"" + indiceMenorI + "\""
                        + " should be lower than index \"" + indiceMaiorI + "\".");
                System.exit(0);
            }
            //System.out.println("indiceMenor: " + indiceMenor + "\nindiceMaior: " + indiceMaior);
            //ehTipoAgregado = false;
        }
    }

    @Override
    public void visitTipoSimples(NodeTipoSimples nodeTipoSimples) {
        if (nodeTipoSimples != null) {
            switch (nodeTipoSimples.tipoSimples) {
                case "integer":
                    nodeTipoSimples.kind = 3;
                    break;
                case "real":
                    nodeTipoSimples.kind = 4;
                    break;
                case "boolean":
                    nodeTipoSimples.kind = 5;
                    break;
                default:
                    nodeTipoSimples.kind = -1;
                    break;
            }
            tA = nodeTipoSimples.kind;
        }
    }

    @Override
    public void visitVariavel(NodeVariavel nodeVariavel) {
        if (nodeVariavel != null) {
            NodeId id;
            if (nodeVariavel.nodeId != null) {
                nodeVariavel.nodeId.visit(this);
                id = t.retrieve(nodeVariavel.nodeId);
                //System.out.println(kind);
                nodeVariavel.setKind(id.getKind());
                nodeVariavel.setDimensao(id.getDimensao());
            }
            if (nodeVariavel.nodeSeletor != null) {
                nodeVariavel.nodeSeletor.visit(this);
            }
            if (nodeVariavel.getDimensao() != quantidadeSeletor) {
                System.out.println("CONTEXT ERROR! -"
                        + " LINE: " + nodeVariavel.nodeId.getLine()
                        + " COLUMN: " + nodeVariavel.nodeId.getColumn()
                        + " - Error in indexing the variable - The variable \""
                        + nodeVariavel.nodeId.getSpelling()
                        + "\" has dimensions \""
                        + nodeVariavel.getDimensao()
                        + "\" and not \""
                        + quantidadeSeletor
                        + "\".");
                System.exit(0);
            }
            //System.out.println("Quantidade do seletor: " + quantidadeSeletor);
            quantidadeSeletor = 0;
        }
    }

}
//(nodeVariavel.nodeId.getColumn() + nodeVariavel.nodeId.getSpelling().length() + 1)