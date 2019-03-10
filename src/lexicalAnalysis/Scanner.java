/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalAnalysis;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import souceFile.SourceFile;

/**
 *
 * @author paulo
 */
public class Scanner {

    private SourceFile sourceFile;
    private char currentChar /*= first source character*/;
    //kind and spelling of the current token
    private byte currentKind;
    private StringBuffer currentSpelling;
    private int endColumn = 0;
    private int column;
    private int contVoidSpace = 1;
    private int line = 0;
    private int aux = 0;
    private boolean flag = false;
    private char p1, p2;

    public Scanner(SourceFile source) {
        sourceFile = source;
        currentChar = sourceFile.readCurrentChar();
        currentSpelling = new StringBuffer("");
        line++;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getContVoidSpace() {
        return contVoidSpace;
    }

    public void setContVoidSpace(int contVoidSpace) {
        this.contVoidSpace = contVoidSpace;
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public char getCurrentChar() {
        return currentChar;
    }

    public void setCurrentChar(char currentChar) {
        this.currentChar = currentChar;
    }

    public byte getCurrentKind() {
        return currentKind;
    }

    public void setCurrentKind(byte currentKind) {
        this.currentKind = currentKind;
    }

    public StringBuffer getCurrentSpelling() {
        return currentSpelling;
    }

    public void setCurrentSpelling(StringBuffer currentSpelling) {
        this.currentSpelling = currentSpelling;
    }

    public SourceFile getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(SourceFile sourceFile) {
        this.sourceFile = sourceFile;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    private void take(char expectedChar) {
        if (currentChar == expectedChar) {
            currentSpelling.append(currentChar);
            currentChar = sourceFile.readCurrentChar();
            //currentChar = next source character;
        } else {
            //report a lexical error
        }
    }

    public void takeIt() {
        currentSpelling.append(currentChar);
        if (currentChar == '\n') {
            aux = 0;
            column = 0;
            endColumn = 0;
            line++;
        } else {
            endColumn++;
        }
        if (currentChar == ' ') {
            contVoidSpace++;
        }
        currentChar = sourceFile.readCurrentChar();
        //currentChar = next source character;
    }

    private boolean isDigit(char c) {
        for (char i = 48; i < 58; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isLetter(char c) {
        for (char i = 97; i < 123; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        for (char i = 65; i < 91; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;

    }

    private boolean isGraphic(char c) {
        for (char i = 32; i < 127; i++) {         //Imprime caracteras gráficos
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }

    private void scanSeparator() {
        switch (currentChar) {
            case '!':
                takeIt();
                while (isGraphic(currentChar)) {
                    takeIt();
                    //take('\n');
                }
                break;
            case ' ':
            case '\t':
            case '\r':
            case '\n':
                takeIt();
                break;
        }
    }

    public void calculateTheNumberOfColumns() {
        column = aux + contVoidSpace;
        aux = endColumn;
        contVoidSpace = 1;

    }

    public Token scan() {
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r') {
            //calculateTheNumberOfColumns();
            scanSeparator();
        }
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
        /*if (currentKind == -1) {
            System.out.print("Token inválido! = ");
        } else {
            System.out.print("Token válido = ");
        }*/
        Token token = new Token(currentKind, currentSpelling.toString(), line, column);
        /*System.out.println("Spelling: " + token.getSpelling()
                + "   Kind: " + token.getKind()
                + "   Column: " + token.getColumn()
                + "   Line: " + token.getLine());*/
        return token;
    }

    private byte scanToken() {
//        if (flag) {
//            currentSpelling.append('.');
//            currentSpelling.append('.');
//            currentChar = sourceFile.readCurrentChar();
//            flag = false;
//            return Token.DOTDOT;
//        }
        if (isLetter(currentChar)) {
            takeIt();
            while (isLetter(currentChar) || isDigit(currentChar)) {
                takeIt();
            }
            calculateTheNumberOfColumns();
            return Token.ID;
        } else if (currentChar == '.') {
            takeIt();
            while (isDigit(currentChar)) {
                takeIt();
                if (!isDigit(currentChar)) {
                    calculateTheNumberOfColumns();
                    return Token.FLOAT_LIT;
                }
            }
            if (currentChar == '.') {
                takeIt();
                calculateTheNumberOfColumns();
                return Token.DOTDOT;
            } else {
                calculateTheNumberOfColumns();
                return Token.DOT;
            }
        } else if (isDigit(currentChar)) {
            takeIt();
            if (currentChar == '.') {
                if (sourceFile.lookahead('.')) {
                    calculateTheNumberOfColumns();
                    return Token.INT_LIT;
                }
//                try {
//                    if (sourceFile.source.read() == '.') {
//                        flag = true;
//                        calculateTheNumberOfColumns();
//                        return Token.INT_LIT;
//                    }
//                } catch (IOException ex) {
//                    //Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
//                }
                takeIt();
                while (isDigit(currentChar)) {
                    takeIt();
                    if (!isDigit(currentChar)) {
                        calculateTheNumberOfColumns();
                        return Token.FLOAT_LIT;
                    }
                }
                calculateTheNumberOfColumns();
                return Token.FLOAT_LIT;
            }
            while (isDigit(currentChar)) {
                takeIt();
                if (currentChar == '.') {
//                    try {
//                        if (sourceFile.source.read() == '.') {
//                            flag = true;
//                            calculateTheNumberOfColumns();
//                            return Token.INT_LIT;
//                        }
//                    } catch (IOException ex) {
//                        //Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    takeIt();
                    if (!isDigit(currentChar)) {
                        calculateTheNumberOfColumns();
                        return Token.FLOAT_LIT;
                    }
                    while (isDigit(currentChar)) {
                        takeIt();
                        if (!isDigit(currentChar)) {
                            calculateTheNumberOfColumns();
                            return Token.FLOAT_LIT;
                        }
                    }
                }
            }
            calculateTheNumberOfColumns();
            return Token.INT_LIT;
        } else {
            switch (currentChar) {
                case '+':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.OP_AD_AD;
                case '-':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.OP_AD_SUB;
                case '*':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.OP_MULT_MULT;
                case '/':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.OP_MULT_DIV;
                case '<':
                    takeIt();
                    if (currentChar == '=') {
                        takeIt();
                        calculateTheNumberOfColumns();
                        return Token.OP_REL_LESSOREQUAL;
                    } else if (currentChar == '>') {
                        takeIt();
                        calculateTheNumberOfColumns();
                        return Token.OP_REL_DIFFERENT;
                    }
                    calculateTheNumberOfColumns();
                    return Token.OP_REL_LESSTHEN;
                case '>':
                    takeIt();
                    if (currentChar == '=') {
                        takeIt();
                        calculateTheNumberOfColumns();
                        return Token.OP_REL_BIGGEROREQUAL;
                    }
                    calculateTheNumberOfColumns();
                    return Token.OP_REL_BIGGERTHEN;
                case '=':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.OP_REL_EQUAL;
                case '[':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.LEFTBRACKET;
                case ']':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.RIGHTBRACKET;
                case '(':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.LEFTPARENTHESIS;
                case ')':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.RIGHTPARENTHESIS;
                case ',':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.COMMA;
                case ':':
                    takeIt();
                    if (currentChar == '=') {
                        takeIt();
                        calculateTheNumberOfColumns();
                        return Token.ASSIGNMENT;
                    }
                    calculateTheNumberOfColumns();
                    return Token.COLON;
                case ';':
                    takeIt();
                    calculateTheNumberOfColumns();
                    return Token.SEMICOLON;
                case '\000':
                    //takeIt();
                    calculateTheNumberOfColumns();
                    return Token.EOT;
                default:
                    takeIt();
                    calculateTheNumberOfColumns();
                    return -1;
            }
        }
    }
}
