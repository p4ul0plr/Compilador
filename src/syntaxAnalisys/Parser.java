/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxAnalisys;

import lexicalAnalysis.Scanner;
import lexicalAnalysis.Token;

/**
 *
 * @author paulo
 */
public class Parser {
    
    private Token currentToken;
    private Scanner scanner;
    
    private void accept (byte expectedKind) {
        if (currentToken.getKind() == expectedKind) {
            currentToken = scanner.scan();
        } else  {
            //report a systatic error
        }
    }
    
    private void acceptIt () {
        currentToken = scanner.scan();
    }
    
    private void parse () {
        currentToken = scanner.scan();
        parsePrograma();
        if (currentToken.getKind() != Token.EOT) {
            //report a systatic error
        } else  {
            //report a systatic error
        }
    }
    
    private void parseAtribuicao () {
        parseVariavel();
        accept(Token.ASSIGNMENT);
        parseExpressao();
    }
    
    private void parseBoolLit () {
        switch(currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                acceptIt();
                break;
            default:
                //report a systatic error
        }
    }
    
    private void parseComando () {
        switch(currentToken.getKind()) {
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
                //report a systatic error
        }
    }
    
    private void parseComandoComposto () {
        accept(Token.BEGIN);
        parseListaDeComandos();
        accept(Token.END);
    }
    
    private void parseCondicional () {
        accept(Token.IF);
        parseExpressao();
        accept(Token.THEN);
        parseComando();
        if (currentToken.getKind() == Token.ELSE) {
            acceptIt();
            parseComando();
        } else  {
            //report a systatic error
        }
        
    }
    
    private void parseCorpo () {
        parseDeclaracao();
        parseComandoComposto();
    }
    
    private void parseDeclaracao () {
        parseDeclaracaoDeVariavel();
    }
    
    private void parseDeclaracaoDeVariavel () {
        accept(Token.VAR);
        parseListadeIds();
        accept(Token.COLON);
        parseTipo();
    }
    
    private void parseDeclaracoes () {
        while (currentToken.getKind() == Token.VAR) {
            parseDeclaracao();
            accept(Token.SEMICOLON);
        }
    }
    
    private void parseDigito () { //Não sei se precisa
        if (currentToken.getKind() >= 48 && currentToken.getKind() <= 57) {
            acceptIt();
        } else  {
            //report a systatic error
        }
    }
    
    private void parseExpressao () {
        parseExpressaoSimples();
        switch(currentToken.getKind()) {
            case Token.OP_REL_BIGGEROREQUAL:
            case Token.OP_REL_BIGGERTHEN:
            case Token.OP_REL_DIFFERENT:
            case Token.OP_REL_EQUAL:
            case Token.OP_REL_LESSOREQUAL:
            case Token.OP_REL_LESSTHEN:
                acceptIt();
                parseExpressaoSimples();
                break;
            default:
                //report a systatic erro    
        }
    }
    
    private void parseExpressaoSimples () {
        parseTermo();
        while (currentToken.getKind() == Token.OP_AD_AD ||
               currentToken.getKind() == Token.OP_AD_OR ||
               currentToken.getKind() == Token.OP_AD_SUB) {
            acceptIt();
            parseTermo();
        }
    }
    
    private void parseFator () {
        switch(currentToken.getKind()) {
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
                //report a systatic error
        }
    }
    
    private void parseFloatLit () { //Não sei se precisa
        switch(currentToken.getKind()) {
            case Token.INT_LIT:
                acceptIt();
                accept(Token.DOT);
                if (currentToken.getKind() == Token.INT_LIT) {
                    acceptIt();
                }
                break;
            case Token.DOT:
                acceptIt();
                parseIntLit();
                break;
            default:
                //report a systatic error
        }
    }
    
    private void parseId () { //Não sei se precisa
        
    }
    
    private void parseIntLit () {  //Não sei se precisa
        
    }
    
    private void parseIterativo () {
        accept(Token.WHILE);
        parseExpressao();
        accept(Token.DO);
        parseComando();
    } 
    
    private void parseLetra () { //Não sei se precisa
        
    }
    
    private void parseListaDeComandos () {
        while (currentToken.getKind() == Token.ID ||
               currentToken.getKind() == Token.IF ||
               currentToken.getKind() == Token.WHILE ||
               currentToken.getKind() == Token.BEGIN ) {
            parseComando();
            accept(Token.SEMICOLON);
        }
    }
    
    private void parseListadeIds () {
        accept(Token.ID);
        while (currentToken.getKind() == Token.COMMA) {
            acceptIt();
            accept(Token.ID);
        }
    }
    
    private void parseLiteral () {
        switch (currentToken.getKind()) {
            case Token.TRUE:
            case Token.FALSE:
                parseBoolLit();
                break;
            case Token.INT_LIT:
                acceptIt();
                break;
            case Token.FLOAT_LIT:
                acceptIt();
                break;
            default:
                //report a systatic error    
        }
    }
    
    private void parseOpAd () {
        switch (currentToken.getKind()) {
            case Token.OP_AD_AD:
            case Token.OP_AD_OR:
            case Token.OP_AD_SUB:
                acceptIt();
                break;
            default:
                //report a systatic error    
        }
    }
    
    private void parseOpMul () {
        switch (currentToken.getKind()) {
            case Token.OP_MULT_AND:
            case Token.OP_MULT_DIV:
            case Token.OP_MULT_MULT:
                acceptIt();
                break;
            default:
                //report a systatic error    
        }         
    }
    
    private void parseOpRel () {
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
                //report a systatic error    
        }       
    }
    
    private void parseOutros () { //Não sei se precisa
         
    }
    
    private void parsePrograma () {
        accept(Token.PROGRAM);
        parseId();
        accept(Token.SEMICOLON);
        parseCorpo();
        accept(Token.DOT);
    }
    
    private void parseSeletor () {
        while (currentToken.getKind() == Token.LEFTBRACKET) {
            acceptIt();
            parseExpressao();
            accept(Token.RIGHTBRACKET);
        }
    }
    
    private void parseTermo () {
        parseFator();
        while (currentToken.getKind() == Token.OP_AD_AD ||
               currentToken.getKind() == Token.OP_AD_OR ||
               currentToken.getKind() == Token.OP_AD_SUB) {
            acceptIt();
            parseFator();
        }        
    }
    
    private void parseTipo () {
        switch (currentToken.getKind()) {
            case Token.ARRAY:
                parseTipoAgregado();
                break;
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                parseTipoSimples();
            default:
                //report a systatic error 
        }
    }
    
    private void parseTipoAgregado () {
        accept(Token.ARRAY);
        accept(Token.LEFTBRACKET);
        parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.DOTDOT);
        parseLiteral(); //Errado? o correto seria <int-lit>?
        accept(Token.RIGHTBRACKET);
        accept(Token.OF);
        parseTipo();
    }
    
    private void parseTipoSimples () {
        switch (currentToken.getKind()) {
            case Token.INTEGER:
            case Token.REAL:
            case Token.BOOLEAN:
                acceptIt();
                break;
            default:
                //report a systatic error    
        }
    }
    
    private void parseVariavel () {
        accept(Token.ID);
        parseSeletor();
    }
    
    private void parseVazio () { //Não sei se precisa
        
    }
}
