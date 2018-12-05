/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analiselexicasintatica;

/**
 *
 * @author paulo
 */
public class Scanner {

    private char currentChar /*= first source character*/;
    //kind and spelling of the current token
    private byte currentKind;
    private StringBuffer currentSpelling;

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
            //currentChar = next source character;
        } else {
            //report a lexical error
        }
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
        //currentChar = next source character;
    }

    private boolean isDigit (char c) {
        for (char i = 48; i < 58; i++) {
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isLetter (char c) {
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

    public boolean isGraphic (char c) {
        for (char i = 32; i < 127; i++) {         //Imprime caracteras gráficos
            //System.out.println("" + i);
            if (c == i) {
                return true;
            }
        }
        return false;
    }
    
    private byte scanToken () {
        if (isLetter(currentChar)) {
            takeIt();
            while (isLetter(currentChar) || isDigit(currentChar)) {
                takeIt();
            }
            return Token.ID;
        } else if (isDigit(currentChar)) {
            takeIt();
            while (isDigit(currentChar)) {
                takeIt();
            }
            return Token.INT_LIT;
        } else {
            return -1;
        }
    }
}
