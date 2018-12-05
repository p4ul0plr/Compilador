/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analiselexicasintatica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author paulo
 */
public class LeitorArquivo {

    public String read(String caminho) {
        String conteudo = "";
        try {
            FileReader arq = new FileReader(caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";
            try {
                linha = lerArq.readLine();
                while (linha != null) {
                    conteudo += (linha+"\n");
                    //linha = lerArq.readLine();
                    //linha = lerArq.mark(0);
                }
                arq.close();
            } catch (IOException ex) {
                conteudo = "Erro ao ler arquivo!";
            }
        } catch (FileNotFoundException ex) {
            conteudo = "Erro ao encontrar arquivo!";
        }
        if (conteudo.contains("Erro")) {
            return "";
        } else {
            return conteudo;
        }
    }

}
