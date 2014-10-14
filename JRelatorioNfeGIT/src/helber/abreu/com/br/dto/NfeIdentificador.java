/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

import java.util.Date;


/**
 *
 * @author Raiane
 */
public class NfeIdentificador {
  
    private long       nroNotaFiscal;
    private Date       dataEmissao;
    
    public long getNroNotaFiscal() {
        return nroNotaFiscal;
    }

    public void setNroNotaFiscal(long nroNotaFiscal) {
        this.nroNotaFiscal = nroNotaFiscal;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    
    

    
    @Override
    public String toString() {
        return "NfeIdentificador{" + "nroNotaFiscal=" + nroNotaFiscal + '}';
    }


    
}
