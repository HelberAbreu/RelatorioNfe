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
public class NfeImposto {
    
    private BigDecimal totalTributo;
    private BigDecimal icms;
    private BigDecimal iof;
    private BigDecimal pis;
    private BigDecimal cofins;

    public BigDecimal getTotalTributo() {
        return totalTributo;
    }

    public void setTotalTributo(BigDecimal totalTributo) {
        this.totalTributo = totalTributo;
    }

    public BigDecimal getIcms() {
        return icms;
    }

    public void setIcms(BigDecimal icms) {
        this.icms = icms;
    }

    public BigDecimal getIof() {
        return iof;
    }

    public void setIof(BigDecimal iof) {
        this.iof = iof;
    }

    public BigDecimal getPis() {
        return pis;
    }

    public void setPis(BigDecimal pis) {
        this.pis = pis;
    }

    public BigDecimal getCofins() {
        return cofins;
    }

    public void setCofins(BigDecimal cofins) {
        this.cofins = cofins;
    }

    @Override
    public String toString() {
        return "NfeImposto{" + "totalTributo=" + totalTributo + ", icms=" + icms + ", iof=" + iof + ", pis=" + pis + ", cofins=" + cofins + '}';
    }
    
    
}
