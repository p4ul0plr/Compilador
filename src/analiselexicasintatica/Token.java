package analiselexicasintatica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author paulo
 */
public class Token {

    public byte kind; //Tipo
    public String spelling; //Soletração
    public int line; //Número de linhas
    public int column; //Número de colunas

    public Token(byte kind, String spelling, int line, int column) {
        this.kind = kind;
        this.spelling = spelling;
        this.line = line;
        this.column = column;
        if (kind == ID) {
            for (byte k = INTEGER; k < OP_MULT_AND; k++) {
                if (spelling.equals(spellings[k])) {
                    this.kind = k;
                    break;
                }
            }
            
        }
    }

    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }

    public String getSpelling() {
        return spelling;
    }

    public void setSpelling(String spelling) {
        this.spelling = spelling;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public final static byte ID = 0,
            INT_LIT = 1,
            FLOAT_LIT = 2,
            INTEGER = 15,
            REAL = 16,
            BEGIN = 17,
            END = 18,
            IF = 19,
            THEN = 20,
            ELSE = 21,
            WHILE = 22,
            DO = 23,
            VAR = 23,
            ARRAY = 24,
            OF = 25,
            TRUE = 26,
            FALSE = 27,
            PROGRAM = 28,
            OP_AD_OR = 5,
            OP_MULT_AND = 8,
            OP_AD_AD = 3,
            OP_AD_SUB = 4,
            OP_MULT_MULT = 6,
            OP_MULT_DIV = 7,
            OP_REL_BIGGERTHEN = 9,
            OP_REL_LESSTHEN = 10,
            OP_REL_EQUAL = 11,
            OP_REL_DIFFERENT = 12,
            OP_REL_BIGGEROFEQUAL = 13,
            OP_REL_LESSOREQUAL = 14,
            DOT = 29,
            DOTDOT = 30,
            LEFTBRACKET = 31,
            RIGHTBRACKET = 32,
            LEFTPARENTHESIS = 33,
            RIGHTPARENTHESIS = 34,
            COMMA = 35,
            COLON = 36,
            SEMICOLON = 37,
            ASSIGNMENT = 38;

    private final static String[] spellings = {
        "<id>",
        "<int-lit>",
        "<float-lit>",
        "integer",
        "real",
        "begin",
        "end",
        "if",
        "then",
        "else",
        "while",
        "do",
        "var",
        "array",
        "of",
        "true",
        "false",
        "program",
        "or",
        "and",
        "+",
        "-",
        "*",
        "/",
        ">",
        "<",
        "=",
        "<>",
        ">=",
        "<=",
        ".",
        "..",
        "[",
        "]",
        "(",
        ")",
        ",",
        ":",
        ";",
        ":="
    };

}
