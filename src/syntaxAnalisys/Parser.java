/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

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

    private void parseAtribuicao() {
        parseVariavel();
        accept(Token.ASSIGNMENT);
        parseExpressao();
    }

    private void parseBoolLit() {
        switch (currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                acceptIt();
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
    }

    private void parseComando() {
        switch (currentToken.getKind()) {
            case Token.ID:
                parseAtribuicao();
                break;
            case Token.IF:
                parseCondicional();
                break;
            case Token.WHILE:
                parseIterativo();
                break;
            case Token.BEGIN:
                parseComandoComposto();
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
    }

    private void parseComandoComposto() {
        accept(Token.BEGIN);
        parseListaDeComandos();
        accept(Token.END);
    }

    private void parseCondicional() {
        accept(Token.IF);
        parseExpressao();
        accept(Token.THEN);
        parseComando();
        //System.out.println("Kind: " + Token.spellings[currentToken.getKind()]);
        if (currentToken.getKind() == Token.ELSE) {
            acceptIt();
            parseComando();
        } else {
            //report a systatic error
        }

    }

    private void parseCorpo() {
        parseDeclaracoes();
        parseComandoComposto();
    }

    private void parseDeclaracao() {
        //if (currentToken.getKind() == Token.VAR) {
        parseDeclaracaoDeVariavel();
        /*} else {
            System.out.println("SYNTAX ERROR! - "
                    + "LINE: " + currentToken.getLine()
                    + " COLUMN: " + (currentToken.getColumn() - 1)
                    + " - A Token of type \"" + Token.spellings[Token.VAR]
                    + "\" was expected and not token " + "\""
                    + currentToken.getSpelling() + "\"");
        }*/
    }

    private void parseDeclaracaoDeVariavel() {
        accept(Token.VAR);
        parseListadeIds();
        accept(Token.COLON);
        parseTipo();
    }

    private void parseDeclaracoes() {
        //f (currentToken.getKind() == Token.VAR) {
        while (currentToken.getKind() == Token.VAR) {
            parseDeclaracao();
            accept(Token.SEMICOLON);
        }
        /*} else {
            System.out.println("SYNTAX ERROR! - "
                    + "LINE: " + currentToken.getLine()
                    + " COLUMN: " + (currentToken.getColumn() - 1)
                    + " - A Token of type \"" + Token.spellings[Token.VAR] 
                    + "\" was expected and not token " + "\"" 
                    + currentToken.getSpelling() + "\"");
        }*/
    }

    private void parseDigito() { //Não sei se precisa
//        if (currentToken.getKind() >= 48 && currentToken.getKind() <= 57) {
//            acceptIt();
//        } else  {
//            //report a systatic error
//        }
    }

    private void parseExpressao() {
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

    private void parseId() { //Não sei se precisa
        accept(Token.ID);
    }

    private void parseIntLit() {  //Não sei se precisa
        accept(Token.INT_LIT);
    }

    private void parseIterativo() {
        accept(Token.WHILE);
        parseExpressao();
        accept(Token.DO);
        parseComando();
    }

    private void parseLetra() { //Não sei se precisa

    }

    private void parseListaDeComandos() {
        while (currentToken.getKind() == Token.ID
                || currentToken.getKind() == Token.IF
                || currentToken.getKind() == Token.WHILE
                || currentToken.getKind() == Token.BEGIN) {
            parseComando();
            accept(Token.SEMICOLON);
        }
    }

    private void parseListadeIds() {
        accept(Token.ID);
        while (currentToken.getKind() == Token.COMMA) {
            acceptIt();
            accept(Token.ID);
        }
    }

    private void parseLiteral() {
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

    private void parsePrograma() {
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.DOT);
    }

    private void parseSeletor() {
        while (currentToken.getKind() == Token.LEFTBRACKET) {
            acceptIt();
            parseExpressao();
            accept(Token.RIGHTBRACKET);
        }
    }

    private void parseTermo() {
        parseFator();
        while (currentToken.getKind() == Token.OP_MULT_AND
                || currentToken.getKind() == Token.OP_MULT_DIV
                || currentToken.getKind() == Token.OP_MULT_MULT) {
            parseOpMul();
            parseFator();
        }
    }

    private void parseTipo() {
        switch (currentToken.getKind()) {
            case Token.ARRAY:
                parseTipoAgregado();
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                parseTipoSimples();
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
    }

    private void parseTipoAgregado() {
        accept(Token.ARRAY);
        accept(Token.LEFTBRACKET);
        parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.DOTDOT);
        parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.RIGHTBRACKET);
        accept(Token.OF);
        parseTipo();
    }

    private void parseTipoSimples() {
        switch (currentToken.getKind()) {
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                acceptIt();
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
    }

    private void parseVariavel() {
        accept(Token.ID);
        parseSeletor();
    }

    private void parseVazio() { //Não sei se precisa

    }
}
