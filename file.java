/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classic_snake;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samit
 */
public class file {
    FileWriter f;
    PrintWriter p;

    String name;
    int score;
    
    public file() {
        try {
            f = new FileWriter("score.txt");
            p = new PrintWriter(f);
            
        } catch (IOException ex) {
            Logger.getLogger(file.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
