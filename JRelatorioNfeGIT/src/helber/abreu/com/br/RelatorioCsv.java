package helber.abreu.com.br;

import helber.abreu.com.br.dto.JUtil;
import helber.abreu.com.br.dto.NfeDetalhe;
import helber.abreu.com.br.dto.NfeNotaFiscal;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Raiane
 */
public class RelatorioCsv {
    
    private boolean ehArqCnpj;
    private boolean ehArqNotaFiscal;
    
    private boolean contemVlrProduto;
    private boolean contemNcm;
    private boolean contemIcms;
    private boolean contemConfins;
    private boolean contemPis;
   
    private List<String> listNcm;
    
    private String nomeArquivo(String cnpj, long numNota) throws Exception{
        try{
            if(ehArqCnpj && ehArqNotaFiscal){
             return GerenteDiretorios.getDiretorio()+"/RelProduto_"+cnpj+"_"+numNota+".csv"; 
            }else{
             return GerenteDiretorios.getDiretorio()+"/RelProduto_"+cnpj+".csv";               
            }
        }catch(Exception e){
            throw  new Exception("Erro ao obter diretório para geração do arquivo: "+e.getMessage());
        }
    }

    public void gerarRelatorio(List<NfeNotaFiscal>colNotaFiscal) throws IOException, Exception{
     StringBuilder arquivo = new StringBuilder();

     
     String cnpjAntigo = "";
     long   numNota    = 0;
     
     boolean ehPrimeiro = true;
     
     if(!ehArqCnpj && !ehArqNotaFiscal){
         ehArqCnpj = true;
     }

     for (NfeNotaFiscal notaFiscal : colNotaFiscal) {
        
        if (
            (ehArqNotaFiscal 
             && (numNota != notaFiscal.getInformacao().getIdentificador().getNroNotaFiscal() || !cnpjAntigo.equalsIgnoreCase(notaFiscal.getInformacao().getEmitente().getCnpj()))
            )
            ||
            (ehArqCnpj && !cnpjAntigo.equalsIgnoreCase(notaFiscal.getInformacao().getEmitente().getCnpj())) 
           )
        { 
            if(!ehPrimeiro){
               fecharArquivo(arquivo, nomeArquivo(cnpjAntigo, numNota)); 
            }else{
             ehPrimeiro = false;
            }
            cnpjAntigo = notaFiscal.getInformacao().getEmitente().getCnpj();
            numNota    = notaFiscal.getInformacao().getIdentificador().getNroNotaFiscal();
           
            arquivo = new StringBuilder();
            gerarCabecalho(arquivo);
           
        }
            
          long ncm = -1;
          long cfop = -1;
          
          double totalValorPrd = 0;
          double totalICMS     = 0;
          double totalPIS      = 0;
          double totalCofins   = 0;
        
          Collections.sort(notaFiscal.getInformacao().getDetalhes());
          
          for (NfeDetalhe detalhe : notaFiscal.getInformacao().getDetalhes()) {
              
              if(!listNcm.contains(String.valueOf(detalhe.getProduto().getNCM()))){
                  continue;
              }
              
              if(ncm == -1){
                 ncm  =  detalhe.getProduto().getNCM();
                 cfop = detalhe.getProduto().getCFOP();
                 
                totalValorPrd = 0;
                totalICMS     = 0;
                totalPIS      = 0;
                totalCofins   = 0;
              }

              if(ncm != detalhe.getProduto().getNCM() || cfop != detalhe.getProduto().getCFOP()){
                 gerarDetalhe( arquivo, 
                               notaFiscal.getInformacao().getIdentificador().getNroNotaFiscal(), 
                               notaFiscal.getInformacao().getIdentificador().getDataEmissao  (), 
                               ncm, 
                               cfop,
                               totalValorPrd, 
                               totalICMS, 
                               totalPIS, 
                               totalCofins);
                 ncm =  detalhe.getProduto().getNCM();
                 cfop = detalhe.getProduto().getCFOP(); 
                totalValorPrd = 0;
                totalICMS     = 0;
                totalPIS      = 0;
                totalCofins   = 0;
              }
                 
              totalValorPrd += detalhe.getProduto().getValorPrd     () == null? 0: detalhe.getProduto().getValorPrd     ().multiply(detalhe.getProduto().getQtdItem()).doubleValue();
              totalICMS     += detalhe.getImposto().getIcms         () == null? 0: detalhe.getImposto().getIcms         ().doubleValue();
              totalPIS      += detalhe.getImposto().getPis          () == null? 0: detalhe.getImposto().getPis          ().doubleValue();
              totalCofins   += detalhe.getImposto().getCofins       () == null? 0: detalhe.getImposto().getCofins       ().doubleValue();
          }
          if(ncm != -1){
           gerarDetalhe(arquivo,
                        notaFiscal.getInformacao().getIdentificador().getNroNotaFiscal(),  
                        notaFiscal.getInformacao().getIdentificador().getDataEmissao  (),
                        ncm, 
                        cfop,
                        totalValorPrd, 
                        totalICMS, 
                        totalPIS, 
                        totalCofins);
          }
         
     }
     fecharArquivo(arquivo, nomeArquivo(cnpjAntigo, numNota)); 

    }
    
    
    private void gerarCabecalho(StringBuilder arquivo){
        
         arquivo.append("NOTA FISCAL;");   
         arquivo.append("DATA EMISSÃO;");   
         arquivo.append("NCM; CFOP;");   
                
        if(contemVlrProduto){
          arquivo.append("VALOR PRODUTO;");  
        }
        
        if(contemIcms){
            arquivo.append("ICMS;");
        }
        if(contemPis){
          arquivo.append("PIS;");
        }  
        if(contemConfins){
          arquivo.append("COFINS;");            
        }
        arquivo.append("\n");
    }

    private void gerarDetalhe(StringBuilder arquivo, long notaFiscal,Date dataEmi, long ncm,long cfop, Double totalValorPrd, Double totalICMS, Double totalPIS, Double totalCofins) {
       
        arquivo.append(notaFiscal).append(";");
        arquivo.append(JUtil.dateToString(dataEmi, "dd/MM/yyyy")).append(";");
        arquivo.append(ncm).append(";");   
        arquivo.append(cfop).append(";");

        if(contemVlrProduto){
          arquivo.append(JUtil.formatarNumeroBr(totalValorPrd)).append(";");  
        }
        if(contemIcms){
          arquivo.append(JUtil.formatarNumeroBr(totalICMS)).append(";");
        }
        if(contemPis){
          arquivo.append(JUtil.formatarNumeroBr(totalPIS)).append(";");
        }          
        if(contemConfins){
          arquivo.append(JUtil.formatarNumeroBr(totalCofins)).append(";");            
        }
        arquivo.append("\n");
    }

    private void fecharArquivo(StringBuilder arquivo, String nomeArquivo) throws IOException {

        System.out.println(nomeArquivo);
        System.out.print(arquivo);
        FileWriter file = new FileWriter(nomeArquivo);
        file.write(arquivo.toString());
        file.flush();
        file.close();
        
    }

    public boolean isEhArqCnpj() {
        return ehArqCnpj;
    }

    public void setEhArqCnpj(boolean ehArqCnpj) {
        this.ehArqCnpj = ehArqCnpj;
    }

    public boolean isEhArqNotaFiscal() {
        return ehArqNotaFiscal;
    }

    public void setEhArqNotaFiscal(boolean ehArqNotaFiscal) {
        this.ehArqNotaFiscal = ehArqNotaFiscal;
    }

    public boolean isContemVlrProduto() {
        return contemVlrProduto;
    }

    public void setContemVlrProduto(boolean contemVlrProduto) {
        this.contemVlrProduto = contemVlrProduto;
    }

    public boolean isContemNcm() {
        return contemNcm;
    }

    public void setContemNcm(boolean contemNcm) {
        this.contemNcm = contemNcm;
    }

    public boolean isContemIcms() {
        return contemIcms;
    }

    public void setContemIcms(boolean contemIcms) {
        this.contemIcms = contemIcms;
    }

    public boolean isContemConfins() {
        return contemConfins;
    }

    public void setContemConfins(boolean contemConfins) {
        this.contemConfins = contemConfins;
    }

    public boolean isContemPis() {
        return contemPis;
    }

    public void setContemPis(boolean contemPis) {
        this.contemPis = contemPis;
    }

    public void setListNcm(List<String> listNcm) {
        this.listNcm = listNcm;
    }
    
}
