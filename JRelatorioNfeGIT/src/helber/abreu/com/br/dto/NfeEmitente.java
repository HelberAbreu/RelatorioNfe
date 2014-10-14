/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

/**
 *
 * @author Raiane
 */
public class NfeEmitente {
    
    private String cnpj;
    private String nome;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "NfeEmitente{" + "cnpj=" + cnpj + ", nome=" + nome + '}';
    }
    
    
}
