/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analiselexicasintatica;

import file.SourceFile;

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

    public Scanner(SourceFile source) {
        sourceFile = source;
        currentChar = sourceFile.readCurrentChar();
        currentSpelling = new StringBuffer("");
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

    public byte scanToken() {
        if (isLetter(currentChar)) {
            takeIt();
            while (isLetter(currentChar) || isDigit(currentChar)) {
                takeIt();
            }
            return Token.ID;
        } else if (currentChar == '.') {
            takeIt();
            while (isDigit(currentChar)) {
                takeIt();
                return Token.FLOAT_LIT;
            }
            if (currentChar == '.') {
                return Token.DOTDOT;
            } else {
                return Token.DOT;
            }
        } else if (isDigit(currentChar)) {
            takeIt();
            if (currentChar == '.') {
                takeIt();
                while (isDigit(currentChar)) {
                    takeIt();
                    return Token.FLOAT_LIT;
                }
                return Token.FLOAT_LIT;
            }
            while (isDigit(currentChar)) {
                takeIt();
                if (sourceFile.readCurrentChar() == '.') {
                    takeIt();
                    return Token.FLOAT_LIT;
                }
            }
            return Token.INT_LIT;
        } else {
            switch (currentChar) {
                case '+':
                    takeIt();
                    return Token.OP_AD_AD;
                case '-':
                    takeIt();
                    return Token.OP_AD_SUB;
                case '*':
                    takeIt();
                    return Token.OP_MULT_MULT;
                case '/':
                    takeIt();
                    return Token.OP_MULT_DIV;
                case '<':
                    takeIt();
                    if (currentChar == '=') {
                        takeIt();
                        return Token.OP_REL_LESSOREQUAL;
                    } else if (currentChar == '>') {
                        takeIt();
                        return Token.OP_REL_DIFFERENT;
                    }
                    return Token.OP_REL_LESSTHEN;
                case '>':
                    takeIt();
                    if (currentChar == '=') {
                        takeIt();
                        return Token.OP_REL_BIGGEROREQUAL;
                    }
                    return Token.OP_REL_BIGGERTHEN;
                case '=':
                    takeIt();
                    return Token.OP_REL_EQUAL;
                case '[':
                    takeIt();
                    return Token.LEFTBRACKET;
                case ']':
                    takeIt();
                    return Token.RIGHTBRACKET;
                case '(':
                    takeIt();
                    return Token.LEFTPARENTHESIS;
                case ')':
                    takeIt();
                    return Token.RIGHTPARENTHESIS;
                case ',':
                    takeIt();
                    return Token.COMMA;
                case ':':
                    takeIt();
                    if (currentChar == '=') {
                       takeIt();
                       return Token.ASSIGNMENT;
                    } 
                    return Token.COLON;
                case ';':
                    takeIt();
                    return Token.SEMICOLON;
                default:
                    return -1;
            }
        }
    }
}
