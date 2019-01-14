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
        }
    }
    
    private void parseAtribuicao () {
        parsevariavel();
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
            acceptIt();
            parseListadeIds();
            accept(Token.COLON);
            parseTipo();
            accept(Token.SEMICOLON);
        }
    }
    
    private void parseDigito () {
        if (currentToken.getKind() >= 48 && currentToken.getKind() <= 57) {
            acceptIt();
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
        }
    }
    
    private void parseExpressaoSimples () {
        
    }
    
    private void parseFator () {
        
    }
    
    private void parseFloatLit () {
        
    }
    
    private void parseId () {
        
    }
    
    private void parseIntLit () {
        
    }
    
    private void parseIterativo () {
        
    } 
    
    private void parseLetra () {
        
    }
    
    private void parseListaDeComandos () {
        
    }
    
    private void parseListadeIds () {
        
    }
    
    private void parseLiteral () {
        
    }
    
    private void parseOpAd () {
        
    }
    
    private void parseOpMul () {
         
    }
    
    private void parseOpRel () {
        
    }
    
    private void parseOutros () {
         
    }
    
    private void parsePrograma () {
        
    }
    
    private void parseSeletor () {
        
    }
    
    private void parseTermo () {
        
    }
    
    private void parseTipo () {
        
    }
    
    private void parseTipoAgregado () {
        
    }
    
    private void parseTipoSimples () {
        
    }
    
    private void parsevariavel () {
        
    }
    
    private void parseVazio () {
        
    }
}
