package analiselexicasintatica;

import file.SourceFile;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author paulo
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
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
        SourceFile sourceFile = new SourceFile("/tmp/arquivo.txt");
        Scanner scanner = new Scanner(sourceFile);
        System.out.println("" + scanner.scanToken());
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
