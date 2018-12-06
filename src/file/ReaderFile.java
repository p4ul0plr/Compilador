/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author paulo
 */
public class ReaderFile {

    private InputStream is = null;
    private InputStreamReader isr = null;
    public BufferedReader br = null;

    public ReaderFile(String path) throws FileNotFoundException, IOException {

        try {
            // open input stream test.txt for reading purpose.
            is = new FileInputStream(path);

            // create new input stream reader
            isr = new InputStreamReader(is);

            // create new buffered reader
            br = new BufferedReader(isr);

            br.mark(100000);

        } catch (Exception e) {
            // exception occurred.
            e.printStackTrace();
        }
    }

    public char readCurrentChar() throws IOException {
        char currentChar = (char) -1;
        br.mark(1);
        currentChar = (char) br.read();
        return currentChar;
    }

    public char teste() throws IOException {
        char currentChar = (char) -1;
        br.reset();
        currentChar = (char) br.read();
        return currentChar;
    }

}
