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
import lexicalAnalysis.Scanner;
import lexicalAnalysis.Token;
import souceFile.SourceFile;

/**
 *
 * @author paulo
 */
public class Parser {

    private Token currentToken;
    public Scanner scanner;

    public Parser(SourceFile source) {
        scanner = new Scanner(source);
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    private void accept(byte expectedKind) {
        if (currentToken.getKind() == expectedKind) {
            currentToken = scanner.scan();
        } else {
            System.out.println("(Accept) SYNTAX ERROR! - "
                    + "LINE: " + currentToken.getLine()
                    + " COLUMN: " + currentToken.getColumn()
                    + " - A Token of type \"" + Token.spellings[expectedKind]
                    + "\" was expected and not token " + "\"" + currentToken.getSpelling() + "\"");
        }
    }

    private void acceptIt() {
        currentToken = scanner.scan();
    }

    public NodePrograma parse() {
        currentToken = scanner.scan();
        return parsePrograma();
        /*if (currentToken.getKind() != Token.EOT) {
            //report a systatic error
        } else {
            //report a systatic error
        }*/
    }

    private NodeAtribuicao parseAtribuicao() {
        NodeAtribuicao a = new NodeAtribuicao();
        a.nodeVariavel = parseVariavel();
        accept(Token.ASSIGNMENT);
        a.nodeExpressao = parseExpressao();
        return a;
    }

    private NodeBoolLit parseBoolLit() {
        NodeBoolLit b = null;
        switch (currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                acceptIt();
                b = new NodeBoolLit(currentToken.spelling);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.TRUE] + "\""
                        + " | \"" + Token.spellings[Token.FALSE] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error
        }
        return b;
    }

    private NodeComando parseComando() {
        NodeComando c = null;
        switch (currentToken.getKind()) {
            case Token.ID:
                c = parseAtribuicao();
                break;
            case Token.IF:
                c = parseCondicional();
                break;
            case Token.WHILE:
                c = parseIterativo();
                break;
            case Token.BEGIN:
                c = parseComandoComposto();
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.ID] + "\""
                        + " | \"" + Token.spellings[Token.IF] + "\""
                        + " | \"" + Token.spellings[Token.WHILE] + "\""
                        + " | \"" + Token.spellings[Token.BEGIN] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error
        }
        return c;
    }

    private NodeComandoComposto parseComandoComposto() {
        NodeComandoComposto c = new NodeComandoComposto();
        accept(Token.BEGIN);
        c.nodeListaDeComandos = parseListaDeComandos();
        accept(Token.END);
        return c;
    }

    private NodeCondicional parseCondicional() {
        NodeCondicional c = new NodeCondicional();
        accept(Token.IF);
        c.nodeExpressao = parseExpressao();
        accept(Token.THEN);
        c.nodeComandoIf = parseComando();
        //System.out.println("Kind: " + Token.spellings[currentToken.getKind()]);
        if (currentToken.getKind() == Token.ELSE) {
            acceptIt();
            c.nodeComandoElse = parseComando();
        } else {
            c.nodeComandoElse = null;
            //report a systatic error
        }
        return c;

    }

    private NodeCorpo parseCorpo() {
        NodeCorpo c = new NodeCorpo();
        c.nodeDeclaracoes = parseDeclaracoes();
        c.nodeComandoComposto = parseComandoComposto();
        return c;
    }

    private NodeDeclaracao parseDeclaracao() {
        //if (currentToken.getKind() == Token.VAR) {
        /*} else {
            System.out.println("SYNTAX ERROR! - "
                    + "LINE: " + currentToken.getLine()
                    + " COLUMN: " + (currentToken.getColumn() - 1)
                    + " - A Token of type \"" + Token.spellings[Token.VAR]
                    + "\" was expected and not token " + "\""
                    + currentToken.getSpelling() + "\"");
        }*/
        return new NodeDeclaracao(parseDeclaracaoDeVariavel());
    }

    private NodeDeclaracaoDeVariavel parseDeclaracaoDeVariavel() {
        NodeDeclaracaoDeVariavel d = new NodeDeclaracaoDeVariavel();
        accept(Token.VAR);
        d.nodeListaDeIds = parseListadeIds();
        accept(Token.COLON);
        d.nodeTipo = parseTipo();
        return d;
    }

    private NodeDeclaracoes parseDeclaracoes() {
        NodeDeclaracoes d, first, last;
        first = null;
        last = null;
        //f (currentToken.getKind() == Token.VAR) {
        while (currentToken.getKind() == Token.VAR) {
            d = new NodeDeclaracoes(parseDeclaracao(), null);
            accept(Token.SEMICOLON);
            if (first == null) {
                first = d;
            } else {
                last.next = d;
            }
            last = d;
        }
        /*} else {
            System.out.println("SYNTAX ERROR! - "
                    + "LINE: " + currentToken.getLine()
                    + " COLUMN: " + (currentToken.getColumn() - 1)
                    + " - A Token of type \"" + Token.spellings[Token.VAR] 
                    + "\" was expected and not token " + "\"" 
                    + currentToken.getSpelling() + "\"");
        }*/
        return first;
    }

    private void parseDigito() { //Não sei se precisa
//        if (currentToken.getKind() >= 48 && currentToken.getKind() <= 57) {
//            acceptIt();
//        } else  {
//            //report a systatic error
//        }
    }

    private NodeExpressao parseExpressao() {
        NodeExpressao e = new NodeExpressao();
        e.nodeExpressaoSimples1 = parseExpressaoSimples();
        switch (currentToken.getKind()) {
            case Token.OP_REL_BIGGEROREQUAL:
            case Token.OP_REL_BIGGERTHEN:
            case Token.OP_REL_DIFFERENT:
            case Token.OP_REL_EQUAL:
            case Token.OP_REL_LESSOREQUAL:
            case Token.OP_REL_LESSTHEN:
                e.nodeOpRel = parseOpRel();
                e.nodeExpressaoSimples2 = parseExpressaoSimples();
                break;
            default:
                e.nodeExpressaoSimples2 = null;
                e.nodeOpRel = null;
            //report a systatic erro    
        }
        return e;
    }

    private NodeExpressaoSimples parseExpressaoSimples() {
        NodeExpressaoSimples eS = new NodeExpressaoSimples();
        NodeExpressaoSimplesComplemento eSC, first, last;
        eS.nodeTermo = parseTermo();
        first  = null;
        last = null;
        while (currentToken.getKind() == Token.OP_AD_AD
                || currentToken.getKind() == Token.OP_AD_OR
                || currentToken.getKind() == Token.OP_AD_SUB) {
            eSC = new NodeExpressaoSimplesComplemento();
            eSC.nodeOpAd = parseOpAd();
            eSC.nodeTermo = parseTermo();
            eSC.next = null;
            if (first == null) {
                first = eSC;
            } else {
                last.next = eSC;
            }
            last = eSC;
        }
        eS.nodeExpressaoSimplesComplemento = first;
        return eS;
    }

    private NodeFator parseFator() {
        NodeFator f = null;
        switch (currentToken.getKind()) {
            case Token.ID:
                f = parseVariavel();
                break;
            case Token.TRUE:
            case Token.FALSE:
            case Token.INT_LIT:
            case Token.FLOAT_LIT:
                f = parseLiteral();
                break;
            case Token.LEFTPARENTHESIS:
                acceptIt();
                f = parseExpressao();
                accept(Token.RIGHTPARENTHESIS);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.ID] + "\""
                        + " | \"" + Token.spellings[Token.TRUE] + "\""
                        + " | \"" + Token.spellings[Token.FALSE] + "\""
                        + " | \"" + Token.spellings[Token.INT_LIT] + "\""
                        + " | \"" + Token.spellings[Token.FLOAT_LIT] + "\""
                        + " | \"" + Token.spellings[Token.LEFTPARENTHESIS] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error
        }
        return f;
    }

    private NodeFloatLit parseFloatLit() { //Não sei se precisa
//        switch(currentToken.getKind()) {
//            case Token.INT_LIT:
//                acceptIt();
//                accept(Token.DOT);
//                if (currentToken.getKind() == Token.INT_LIT) {
//                    acceptIt();
//                }
//                break;
//            case Token.DOT:
//                acceptIt();
//                parseIntLit();
//                break;
//            default:
//                //report a systatic error
//        }
        accept(Token.FLOAT_LIT);
        return new NodeFloatLit(Token.spellings[Token.FLOAT_LIT]);
    }

    private NodeId parseId() { //Não sei se precisa
        accept(Token.ID);
        return new NodeId(Token.spellings[Token.ID]);
    }

    private NodeIntLit parseIntLit() {  //Não sei se precisa
        accept(Token.INT_LIT);
        return new NodeIntLit(Token.spellings[Token.INT_LIT]);
    }

    private NodeIterativo parseIterativo() {
        NodeIterativo i = new NodeIterativo();
        accept(Token.WHILE);
        i.nodeExpressao = parseExpressao();
        accept(Token.DO);
        i.nodeComando = parseComando();
        return i;
    }

    private void parseLetra() { //Não sei se precisa

    }

    private NodeListaDeComandos parseListaDeComandos() {
        NodeListaDeComandos lC , first, last;
        first = null;
        last = null;
        while (currentToken.getKind() == Token.ID
                || currentToken.getKind() == Token.IF
                || currentToken.getKind() == Token.WHILE
                || currentToken.getKind() == Token.BEGIN) {
            lC = new NodeListaDeComandos();
            lC.nodeComando = parseComando();
            accept(Token.SEMICOLON);
            lC.next = null;
            if (first == null) {
                first = lC;
            } else {
                last.next = lC;
            }
            last = lC;
        }
        return first;
    }

    private NodeListaDeIds parseListadeIds() {
        NodeListaDeIds l, first, last;
        accept(Token.ID);
        l = new NodeListaDeIds(new NodeId(Token.spellings[Token.ID]), null);
        first = l;
        last = l;
        while (currentToken.getKind() == Token.COMMA) {
            acceptIt();
            accept(Token.ID);
            l = new NodeListaDeIds(new NodeId(Token.spellings[Token.ID]), null);
            last.next = l;
            last = l;
        }
        return first;
    }

    private NodeLiteral parseLiteral() {
        NodeLiteral l = null;
        switch (currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                l = parseBoolLit();
                break;
            case Token.INT_LIT:
                l = parseIntLit();
                break;
            case Token.FLOAT_LIT:
                l = parseFloatLit();
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.TRUE] + "\""
                        + " | \"" + Token.spellings[Token.FALSE] + "\""
                        + " | \"" + Token.spellings[Token.INT_LIT] + "\""
                        + " | \"" + Token.spellings[Token.FLOAT_LIT] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error    
        }
        return l;
    }

    private NodeOpAd parseOpAd() {
        NodeOpAd o = null;
        switch (currentToken.getKind()) {
            case Token.OP_AD_AD:
            case Token.OP_AD_OR:
            case Token.OP_AD_SUB:
                acceptIt();
                o = new NodeOpAd(currentToken.spelling);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.OP_AD_AD] + "\""
                        + " | \"" + Token.spellings[Token.OP_AD_OR] + "\""
                        + " | \"" + Token.spellings[Token.OP_AD_SUB] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error    
        }
        return o;
    }

    private NodeOpMul parseOpMul() {
        NodeOpMul o = null;
        switch (currentToken.getKind()) {
            case Token.OP_MULT_AND:
            case Token.OP_MULT_DIV:
            case Token.OP_MULT_MULT:
                acceptIt();
                o = new NodeOpMul(currentToken.spelling);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.OP_MULT_AND] + "\""
                        + " | \"" + Token.spellings[Token.OP_MULT_DIV] + "\""
                        + " | \"" + Token.spellings[Token.OP_MULT_MULT] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error    
        }
        return o;
    }

    private NodeOpRel parseOpRel() {
        NodeOpRel o = null;
        switch (currentToken.getKind()) {
            case Token.OP_REL_BIGGEROREQUAL:
            case Token.OP_REL_BIGGERTHEN:
            case Token.OP_REL_DIFFERENT:
            case Token.OP_REL_EQUAL:
            case Token.OP_REL_LESSOREQUAL:
            case Token.OP_REL_LESSTHEN:
                acceptIt();
                o = new NodeOpRel(currentToken.spelling);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.OP_REL_BIGGEROREQUAL] + "\""
                        + " | \"" + Token.spellings[Token.OP_REL_BIGGERTHEN] + "\""
                        + " | \"" + Token.spellings[Token.OP_REL_DIFFERENT] + "\""
                        + " | \"" + Token.spellings[Token.OP_REL_EQUAL] + "\""
                        + " | \"" + Token.spellings[Token.OP_REL_LESSOREQUAL] + "\""
                        + " | \"" + Token.spellings[Token.OP_REL_LESSTHEN] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error    
        }
        return o;
    }

    private void parseOutros() { //Não sei se precisa

    }

    private NodePrograma parsePrograma() {
        NodePrograma p = new NodePrograma(); //Deletar o construtor
        accept(Token.PROGRAM);
        p.nodeId = parseId();
        accept(Token.SEMICOLON);
        p.nodeCorpo = parseCorpo();
        accept(Token.DOT);
        return p;
    }

    private NodeSeletor parseSeletor() {
        NodeSeletor s, first, last;
        first = null;
        last = null;
        while (currentToken.getKind() == Token.LEFTBRACKET) {
            acceptIt();
            s = new NodeSeletor();
            s.nodeExpressao = parseExpressao();
            accept(Token.RIGHTBRACKET);
            s.next = null;
            if (first == null) {
                first = s;
            } else {
                last.next = s;
            }
            last = s;
        }
        return first;
    }

    private NodeTermo parseTermo() { //Voltar aqui em algum momento para encarrar isso!!!
        NodeTermo t = new NodeTermo();
        NodeTermoComplemento tC, first, last;
        t.nodeFator = parseFator();
        first = null;
        last = null;
        while (currentToken.getKind() == Token.OP_MULT_AND
                || currentToken.getKind() == Token.OP_MULT_DIV
                || currentToken.getKind() == Token.OP_MULT_MULT) {
            tC = new NodeTermoComplemento();
            tC.nodeOpMul = parseOpMul();
            tC.nodeFator = parseFator();
            tC.next = null;
            if (first == null) {
                first = tC;
            } else {
                last.next = tC;
            }
            last = tC;
        }
        t.nodeTermoComplemento = first;
        return t;
    }

    private NodeTipo parseTipo() {
        NodeTipo t = null;
        switch (currentToken.getKind()) {
            case Token.ARRAY:
                t = parseTipoAgregado();
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                t = parseTipoSimples();
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.ARRAY] + "\""
                        + " | \"" + Token.spellings[Token.INTEGER] + "\""
                        + " | \"" + Token.spellings[Token.REAL] + "\""
                        + " | \"" + Token.spellings[Token.BOOLEAN] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error 
        }
        return t;
    }

    private NodeTipoAgregado parseTipoAgregado() {
        NodeTipoAgregado tA = new NodeTipoAgregado();
        accept(Token.ARRAY);
        accept(Token.LEFTBRACKET);
        tA.nodeLiteral1 = parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.DOTDOT);
        tA.nodeLiteral2 = parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.RIGHTBRACKET);
        accept(Token.OF);
        tA.nodeTipo = parseTipo();
        return tA;
    }

    private NodeTipoSimples parseTipoSimples() {
        NodeTipoSimples tS = null;
        switch (currentToken.getKind()) {
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                acceptIt();
                tS = new NodeTipoSimples(currentToken.spelling);
                break;
            default:
                System.out.println("SYNTAX ERROR! - "
                        + "LINE: " + currentToken.getLine()
                        + " COLUMN: " + currentToken.getColumn()
                        + " - A Token of type \"" + Token.spellings[Token.INTEGER] + "\""
                        + " | \"" + Token.spellings[Token.REAL] + "\""
                        + " | \"" + Token.spellings[Token.BOOLEAN] + "\""
                        + " was expected and not token " + "\""
                        + currentToken.getSpelling() + "\"");
            //report a systatic error    
        }
        return tS;
    }

    private NodeVariavel parseVariavel() {
        NodeVariavel v = new NodeVariavel();
        accept(Token.ID);
        v.nodeId = new NodeId(Token.spellings[Token.ID]);
        v.nodeSeletor = parseSeletor();
        return v;
    }

    private void parseVazio() { //Não sei se precisa

    }
}
