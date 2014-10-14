/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Raiane
 */
public class GerenteDiretorios {
    
    public static boolean atualizarDiretorio(String caminho) throws IOException{
        FileWriter file = new FileWriter("diretorios.txt");
        file.write(caminho);
        file.flush();
        file.close();
    
        return true;
    }
    
    public static String getDiretorio() throws  Exception{
            BufferedReader buffRead = new BufferedReader(new FileReader("diretorios.txt"));
            String caminho = buffRead.readLine();
            buffRead.close();
            return caminho;
        }
    
    public static void main(String[] arg) throws Exception{
        System.out.println(getDiretorio());
        atualizarDiretorio("c://testes/");
        System.out.println(getDiretorio());
        
    }
}
