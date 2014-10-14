/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

import java.math.BigDecimal;

/**
 *
 * @author Raiane
 */
public class NfeProduto {
    
    private long codPrd;
    private long NCM;
    private BigDecimal qtdItem;
    private BigDecimal valorPrd;
    private long CFOP;
    
    public long getCodPrd() {
        return codPrd;
    }

    public void setCodPrd(long codPrd) {
        this.codPrd = codPrd;
    }

    public long getNCM() {
        return NCM;
    }

    public void setNCM(long NCM) {
        this.NCM = NCM;
    }

    public BigDecimal getValorPrd() {
        return valorPrd;
    }

    public void setValorPrd(BigDecimal valorPrd) {
        this.valorPrd = valorPrd;
    }

    public long getCFOP() {
        return CFOP;
    }

    public void setCFOP(long CFOP) {
        this.CFOP = CFOP;
    }

    public BigDecimal getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(BigDecimal qtd) {
        this.qtdItem = qtd;
    }
    
    

    @Override
    public String toString() {
        return "NfeProduto{" + "codPrd=" + codPrd + ", NCM=" + NCM + ", valorPrd=" + valorPrd + ", CFOP=" + CFOP + '}';
    }

    
}
