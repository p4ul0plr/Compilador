/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalAnalysis;

import TreeDrawer.Printer;
import abstractSyntaxTrees.NodePrograma;
import souceFile.SourceFile;
import java.io.IOException;
import syntaxAnalisys.Parser;

/**
 *
 * @author paulo
 */
public class Compiler {

    /**
     */
    public static final char EOT = '\u0000';

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        for (char i = 32; i < 127; i++) {         //Imprime caracteras gráficos
//            System.out.println(""+i);
//        }

//        Scanner scanner = new Scanner();
//        System.out.println("" + scanner.isDigit('8')); //Testando o isDigit()
//        Scanner scanner = new Scanner();
//        System.out.println("" + scanner.isLetter('A')); //testando o isLetter()
//        Scanner scanner = new Scanner();
//        System.out.println("" + scanner.isGraphic('@'));
//        LeitorArquivo arq = new LeitorArquivo();
//        System.out.println(""+arq.read("/tmp/arquivo.txt"));
        SourceFile sourceFile = new SourceFile("/home/paulo/NetBeansProjects/Compilador/src/souceFile/file.txt");
        Parser parser = new Parser(sourceFile);
        /*/ ---------------- IMPRIMINDO TOKENS ------------------
        System.out.println("---------------- IMPRIMINDO TOKENS ------------------");
        Token token;
        while (parser.scanner.getCurrentChar() != EOT) {
            token = parser.scanner.scan();
            System.out.println("Spelling: " + token.getSpelling() 
                    + "   Kind: " + token.getKind() 
                    + "   Column: " + token.getColumn() 
                    + "   Line: " + token.getLine());

        }
        System.out.println("---------------- IMPRIMINDO TOKENS ------------------");
        // ---------------- IMPRIMINDO TOKENS ------------------*/
        Printer printer = new Printer();
        NodePrograma nodePrograma;
        nodePrograma = parser.parse();
        printer.print(nodePrograma);

        //System.out.println("" + sourceFile.readCurrentChar());
        //System.out.println("" + scanner.scanToken());
//        StringBuffer currentSpelling = new StringBuffer("teste");
//        currentSpelling.append(sourceFile.readCurrentChar());
//        System.out.println("" + currentSpelling);
//        System.out.print("" + readerFile.readCurrentChar());
//        System.out.print("" + readerFile.readCurrentChar());
//        System.out.print("" + readerFile.readCurrentChar());
//        System.out.print("" + readerFile.readCurrentChar());
//        System.out.print("" + readerFile.readCurrentChar());
//        System.out.println("" + readerFile.readCurrentChar());
        //System.out.println(""+readerFile.teste());
        //System.out.println(""+readerFile.teste());
    }

}
