package helber.abreu.com.br.dto;

/**
 *
 * @author Raiane
 */
public class NfeDetalhe implements Comparable<NfeDetalhe> {
    
    private NfeProduto produto;
    private NfeImposto imposto;

    public NfeProduto getProduto() {
        return produto;
    }

    public void setProduto(NfeProduto produto) {
        this.produto = produto;
    }

    public NfeImposto getImposto() {
        return imposto;
    }

    public void setImposto(NfeImposto imposto) {
        this.imposto = imposto;
    }

    @Override
    public String toString() {
        return "NfeDetalhe{" + "produto=" + produto + ", imposto=" + imposto + '}';
    }

    
    @Override
    public int compareTo(NfeDetalhe o) {
        
        if(this.getProduto().getNCM() == o.getProduto().getNCM() && this.getProduto().getCFOP() == o.getProduto().getCFOP()){
            return 0;
        }
        if(this.getProduto().getNCM() > o.getProduto().getNCM()){
            return 1;
        }
        if(this.getProduto().getNCM() < o.getProduto().getNCM()){
            return -1;
        }
        if(this.getProduto().getCFOP() > o.getProduto().getCFOP()){
            return 1;
        }
        return -1;
    }
    
    
}
