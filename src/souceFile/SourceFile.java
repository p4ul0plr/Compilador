/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souceFile;

//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

/**
 *
 * @author paulo
 */
public class SourceFile {

    public static final char EOL = '\n';
    public static final char EOT = '\u0000';

//    private InputStream is = null;
//    private InputStreamReader isr = null;
//    public BufferedReader br = null;
    public File sourceFile;
    public FileInputStream source;
    private InputStreamReader openFile;
    private PushbackReader reader;
    //public int currentLine;

    public SourceFile(String path) {

        try {
            // open input stream test.txt for reading purpose.
//            is = new FileInputStream(path);
//
//            // create new input stream reader
//            isr = new InputStreamReader(is);
//
//            // create new buffered reader
//            br = new BufferedReader(isr);
//
//            br.mark(100000);
            sourceFile = new File(path);
            source = new FileInputStream(sourceFile);
            openFile = new InputStreamReader(source);
            reader = new PushbackReader(openFile);
        } catch (IOException ex) {
            //exception occurred.
            sourceFile = null;
            source = null;
            openFile = null;
            reader = null;
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public FileInputStream getSource() {
        return source;
    }

    public void setSource(FileInputStream source) {
        this.source = source;
    }

//    public int getCurrentLine() {
//        return currentLine;
//    }
//
//    public void setCurrentLine(int currentLine) {
//        this.currentLine = currentLine;
//    }
//    public char readCurrentChar() {
//        try {
//            char currentChar = (char) -1;
//            br.mark(1);
//            if (currentChar == -1) {
//                currentChar = EOT;
//            }
//            currentChar = (char) br.read();
//            return currentChar;
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            return EOT;
//        }
//    }
    public char readCurrentChar() {
        try {
            int c = reader.read();
            if (c == -1) {
                c = EOT;
            } else if (c == EOL) {
                //currentLine++;
            }
            return (char) c;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return EOT;
        }
    }

    public boolean lookahead(int c) {
        try {
            int next = this.reader.read();
            if (next == -1) {
                return false;
            }
            if (next == c) {
                this.reader.unread(next);
                return true;
            } else {
                this.reader.unread(next);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }
//    public char teste() throws IOException {
//        char currentChar = (char) -1;
//        br.reset();
//        currentChar = (char) br.read();
//        return currentChar;
//    }
}
