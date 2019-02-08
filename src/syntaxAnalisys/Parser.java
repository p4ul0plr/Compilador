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
import abstractSyntaxTrees.NodeId;
import abstractSyntaxTrees.NodeIterativo;
import abstractSyntaxTrees.NodeListaDeComandos;
import abstractSyntaxTrees.NodeListaDeIds;
import abstractSyntaxTrees.NodeLiteral;
import abstractSyntaxTrees.NodePrograma;
import abstractSyntaxTrees.NodeSeletor;
import abstractSyntaxTrees.NodeTermo;
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

    public void parse() {
        currentToken = scanner.scan();
        parsePrograma();
        if (currentToken.getKind() != Token.EOT) {
            //report a systatic error
        } else {
            //report a systatic error
        }
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
        c.nodeComandoIf = parseComando();
        accept(Token.THEN);
        c.nodeExpressao = parseExpressao();
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
            accept(Token.SEMICOLON);
            d = new NodeDeclaracoes(parseDeclaracao(), null);
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
        parseExpressaoSimples();
        switch (currentToken.getKind()) {
            case Token.OP_REL_BIGGEROREQUAL:
            case Token.OP_REL_BIGGERTHEN:
            case Token.OP_REL_DIFFERENT:
            case Token.OP_REL_EQUAL:
            case Token.OP_REL_LESSOREQUAL:
            case Token.OP_REL_LESSTHEN:
                parseOpRel();
                parseExpressaoSimples();
                break;
            default:
            //report a systatic erro    
        }
        return null;
    }

    private void parseExpressaoSimples() {
        parseTermo();
        while (currentToken.getKind() == Token.OP_AD_AD
                || currentToken.getKind() == Token.OP_AD_OR
                || currentToken.getKind() == Token.OP_AD_SUB) {
            parseOpAd();
            parseTermo();
        }
    }

    private void parseFator() {
        switch (currentToken.getKind()) {
            case Token.ID:
                parseVariavel();
                break;
            case Token.TRUE:
            case Token.FALSE:
            case Token.INT_LIT:
            case Token.FLOAT_LIT:
                parseLiteral();
                break;
            case Token.LEFTPARENTHESIS:
                acceptIt();
                parseExpressao();
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
    }

    private void parseFloatLit() { //Não sei se precisa
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
    }

    private NodeId parseId() { //Não sei se precisa
        NodeId i;
        accept(Token.ID);
        return new NodeId(Token.spellings[Token.ID]);
    }

    private void parseIntLit() {  //Não sei se precisa
        accept(Token.INT_LIT);
    }

    private NodeIterativo parseIterativo() {
        accept(Token.WHILE);
        parseExpressao();
        accept(Token.DO);
        parseComando();
        return null;
    }

    private void parseLetra() { //Não sei se precisa

    }

    private NodeListaDeComandos parseListaDeComandos() {
        while (currentToken.getKind() == Token.ID
                || currentToken.getKind() == Token.IF
                || currentToken.getKind() == Token.WHILE
                || currentToken.getKind() == Token.BEGIN) {
            parseComando();
            accept(Token.SEMICOLON);
        }
        return null;
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
        switch (currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                parseBoolLit();
                break;
            case Token.INT_LIT:
                parseIntLit();
                break;
            case Token.FLOAT_LIT:
                parseFloatLit();
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
        return null;
    }

    private void parseOpAd() {
        switch (currentToken.getKind()) {
            case Token.OP_AD_AD:
            case Token.OP_AD_OR:
            case Token.OP_AD_SUB:
                acceptIt();
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
    }

    private void parseOpMul() {
        switch (currentToken.getKind()) {
            case Token.OP_MULT_AND:
            case Token.OP_MULT_DIV:
            case Token.OP_MULT_MULT:
                acceptIt();
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
    }

    private void parseOpRel() {
        switch (currentToken.getKind()) {
            case Token.OP_REL_BIGGEROREQUAL:
            case Token.OP_REL_BIGGERTHEN:
            case Token.OP_REL_DIFFERENT:
            case Token.OP_REL_EQUAL:
            case Token.OP_REL_LESSOREQUAL:
            case Token.OP_REL_LESSTHEN:
                acceptIt();
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
            s = new NodeSeletor(parseExpressao(), null);
            accept(Token.RIGHTBRACKET);
            if (first == null) {
                first = s;
            } else {
                last.next = s;
            }
            last = s;
        }
        return first;
    }

    private void parseTermo() { //Voltar aqui em algum momento para encarrar isso!!!
        parseFator();
        while (currentToken.getKind() == Token.OP_MULT_AND
                || currentToken.getKind() == Token.OP_MULT_DIV
                || currentToken.getKind() == Token.OP_MULT_MULT) {
            parseOpMul();
            parseFator();
        }
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
        accept(Token.ID);
        return new NodeVariavel(new NodeId(Token.spellings[Token.ID]), parseSeletor());
    }

    private void parseVazio() { //Não sei se precisa

    }
}
