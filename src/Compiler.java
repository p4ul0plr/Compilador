/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Alunos: PAULO ROBERTO DA SILVA NOBREGA e EDUARDO CAVALCANTE ARAUJO
 * Disciplina: Compiladores 
 * Professor: Marcus Vinícius Midena Ramos
 * ---------- Projeto Final da disciplina de compiladores ------------
 */

import TreeDrawer.Printer;
import abstractSyntaxTree.NodePrograma;
import souceFile.SourceFile;
import java.io.IOException;
import contextAnalysis.Checker;
import java.util.Scanner;
import lexicalAnalysis.Token;
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
        Scanner ler = new Scanner(System.in);
        String programaFonte;
        int opcao;
        System.out.print("Caminho do programa fonte: ");
        //programaFonte = ler.nextLine();
        programaFonte = "/home/paulo/NetBeansProjects/Compilador/src/souceFile/file.txt";
        SourceFile sourceFile = new SourceFile(programaFonte);
        do {
            System.out.println("Selecione até que etapa da compilação gostaria de executar: ");
            System.out.println("1 - Análise Léxica");
            System.out.println("2 - Análise Sintática");
            System.out.println("3 - Impressão da Árvore");
            System.out.println("4 - Análise de Contexto");
            System.out.println("5 - Geração de Código");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");
            try {
                opcao = Integer.parseInt(ler.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }
        } while (opcao < 1 || opcao > 6);
        Parser parser = new Parser(sourceFile);
        NodePrograma nodePrograma;
        switch (opcao) {
            case 1:
                System.out.println("################################################################");
                System.out.println("####################### ANÁLISE LÉXICA #########################");
                System.out.println("################################################################");
                Token token;
                while (parser.scanner.getCurrentChar() != EOT) {
                    token = parser.scanner.scan();
                    if (parser.scanner.getCurrentKind() == -1) {
                        System.out.print("Token inválido! = ");
                    } else {
                        System.out.print("Token válido = ");
                    }
                    System.out.println("Spelling: " + token.getSpelling()
                            + "   Kind: " + token.getKind()
                            + "   Column: " + token.getColumn()
                            + "   Line: " + token.getLine());
                }
                sourceFile.source.close();
                break;
            case 2:
                System.out.println("################################################################");
                System.out.println("####################### ANÁLISE SINTÁTICA ######################");
                System.out.println("################################################################");
                parser.parse();
                sourceFile.source.close();
                break;
            case 3:
                System.out.println("################################################################");
                System.out.println("#################### IMPRESSÃO DA ÁRVORE #######################");
                System.out.println("################################################################");
                Printer printer = new Printer();
                nodePrograma = parser.parse();
                printer.print(nodePrograma);
                sourceFile.source.close();
                break;
            case 4:
                System.out.println("################################################################");
                System.out.println("#################### ANÁLISE DE CONTEXTO #######################");
                System.out.println("################################################################");
                Checker checker = new Checker();
                nodePrograma = parser.parse();
                checker.Check(nodePrograma);
                checker.ImpimeIdentificationTable();
                sourceFile.source.close();
                break;
            case 5:
                System.out.println("################################################################");
                System.out.println("###################### GERAÇÃO DE CÓDIGO #######################");
                System.out.println("################################################################");
                sourceFile.source.close();
                break;
            case 6:
                sourceFile.source.close();
                System.exit(0);
        }
//        String cadeias_aceitas = "/home/paulo/NetBeansProjects/Compilador/src/souceFile/cadeias_aceitas.txt";
//        String cadeias_recusadas = "/home/paulo/NetBeansProjects/Compilador/src/souceFile/cadeias_recusadas.txt";
//        String file = "/home/paulo/NetBeansProjects/Compilador/src/souceFile/file.txt";
        /*/ -- -- -- -- -- -- -- --IMPRIMINDO 
                TOKENS------------------
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
 /*Printer printer = new Printer();
        Checker checker = new Checker();
        NodePrograma nodePrograma;
        nodePrograma = parser.parse();*/
 /*printer.print(nodePrograma);
        checker.Check(nodePrograma);*/

 /*StringBuffer a = new StringBuffer();
        StringBuffer b = new StringBuffer();
        a.append("a");
        b.append("a");
        String aa, bb;
        aa = a.toString();
        bb = b.toString();
        String aaa = "a", bbb = "a";
        if (aa.equals(bb.toString())) {
            System.out.println("Elementos iguais!");
        }*/
 /*String id = "i";
        //checker.t.identificadores.add(id);
        if (checker.t.identificadores.contains(id)) {
            System.out.println("Identificador " + id + " já foi declarado!");
        } else {
            checker.t.identificadores.add(id);
        }*/
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
