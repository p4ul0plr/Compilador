/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalAnalysis;

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
    private int column = 0;
    private int line = 0;
    private int aux = -1;
    private int aux2 = 0;

    public Scanner(SourceFile source) {
        sourceFile = source;
        currentChar = sourceFile.readCurrentChar();
        currentSpelling = new StringBuffer("");
        line++;
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

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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
            column = 0;
            line++;
        } else {
            column++;
            aux++;
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
        //System.out.println("column (antes): " + column + " aux (antes): " + aux);
//        column -= aux;
//        aux = -1;
        //System.out.println("column (depois): " + column + " aux (depois): " + aux);
    }

    public Token scan() {
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r') {
            calculateTheNumberOfColumns();
            scanSeparator();
        }
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
        return new Token(currentKind, currentSpelling.toString());
    }

    private byte scanToken() {
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
                    takeIt();
                    if (!isDigit(currentChar)) {
                        calculateTheNumberOfColumns();
                        return Token.FLOAT_LIT;
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
                    calculateTheNumberOfColumns();
                    return Token.EOT;
                default:
                    calculateTheNumberOfColumns();
                    return -1;
            }
        }
    }
}
