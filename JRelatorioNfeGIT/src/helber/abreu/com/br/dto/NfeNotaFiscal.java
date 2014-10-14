/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

/**
 *
 * @author Raiane
 */
public class NfeNotaFiscal {

    private NfeInformacao informacao;

    public NfeInformacao getInformacao() {
        return informacao;
    }

    public void setInformacao(NfeInformacao informacao) {
        this.informacao = informacao;
    }

    @Override
    public String toString() {
        return "NfeNotaFiscal{" + "informacao=" + informacao + '}';
    }
    
    
    
}
