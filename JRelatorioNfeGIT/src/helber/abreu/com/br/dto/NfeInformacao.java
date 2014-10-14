/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br.dto;

import java.util.List;

/**
 *
 * @author Raiane
 */
public class NfeInformacao {
   
    private String id;
    private NfeIdentificador identificador;
    private NfeEmitente emitente;
//  private NfeDestinatario destinatario;
    private List<NfeDetalhe> detalhes;  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NfeIdentificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(NfeIdentificador identificador) {
        this.identificador = identificador;
    }

    public NfeEmitente getEmitente() {
        return emitente;
    }

    public void setEmitente(NfeEmitente emitente) {
        this.emitente = emitente;
    }

    public List<NfeDetalhe> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<NfeDetalhe> detalhes) {
        this.detalhes = detalhes;
    }

    @Override
    public String toString() {
        return "NfeInformacao{" + "id=" + id + ", identificador=" + identificador + ", emitente=" + emitente + ", detalhes=" + detalhes + "}\n";
    }
    
    
    
}
