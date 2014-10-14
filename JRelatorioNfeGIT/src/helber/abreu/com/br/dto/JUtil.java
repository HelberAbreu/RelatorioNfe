/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raiane
 */
public class JUtil {
 
    private static NumberFormat formatador;

    private JUtil() {
    }

    public static String dateToString(Date data, String formato){
        SimpleDateFormat format =  new SimpleDateFormat(formato);

        return format.format(data);
    }
    
    public static Date StringToDate(String data, String formato) throws Exception{
        SimpleDateFormat format =  new SimpleDateFormat(formato);
        
        try {
            return (Date)format.parse(data); 
        } catch (Exception ex) {
            throw new Exception("Erro ao converter data! Motivo: "+ex.getMessage());
        }
    }
    
    public static String formatarNumeroBr(Double numero){
     initNumberFormat();
     return formatador.format(numero);
    }
    
    private static void initNumberFormat(){if(formatador == null){formatador = NumberFormat.getInstance(new Locale("pt","br"));}}
   
            
}
