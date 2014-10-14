/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raiane
 */
public class GerenteDeNCM {
    
    
    public List<String> obterListaNcm() throws  Exception{
        BufferedReader buffRead = new BufferedReader(new FileReader("listaNCM.txt"));
        List<String> lista = new ArrayList<String>();
        
        do{
            lista.add(buffRead.readLine());
            
        }while(buffRead.ready());
        
        buffRead.close();

        return lista;
    }
    
    
    
}
