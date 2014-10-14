/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helber.abreu.com.br;

import helber.abreu.com.br.dto.JUtil;
import helber.abreu.com.br.dto.NfeEmitente;
import helber.abreu.com.br.dto.NfeInformacao;
import helber.abreu.com.br.dto.NfeImposto;
import helber.abreu.com.br.dto.NfeNotaFiscal;
import helber.abreu.com.br.dto.NfeDetalhe;
import helber.abreu.com.br.dto.NfeIdentificador;
import helber.abreu.com.br.dto.NfeProduto;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Helber
 */
public class ConfiguracaoXML {

    
        public static void main(String[] arg) throws Exception{
          ConfiguracaoXML c = new ConfiguracaoXML();
          
          File file = new File("C:\\Users\\Raiane\\Downloads\\NFe_XML_30082014163357");
          
          c.extrairNotasFiscais(file.listFiles());
        }

    public List<NfeNotaFiscal> extrairNotasFiscais(File[] colecaoNotasXML) throws Exception {

        List<NfeNotaFiscal> colecaoNfe = new ArrayList<NfeNotaFiscal>();
        
        for (File file : colecaoNotasXML) {

            if(!file.getName().contains(".xml")){
               continue; 
            }
            Document doc = null;
            SAXBuilder builder = new SAXBuilder();
            
            try {
                //Carregando o arquivo XML!!
                doc = builder.build(file);
            } catch (Exception e) {
                System.out.printf("Não foi possivel ler o arquivo XML!!!\n");
                e.printStackTrace();
            }

            Element configuracao = doc.getRootElement();
            List<Element> lista = configuracao.getChildren();
            
            //Criando nota Fiscal...
            NfeNotaFiscal notaFiscal = new NfeNotaFiscal();
            
            for (Element nefProc : lista) {
                
                //Só processo filhos de Nfe
                if(!nefProc.getName().equalsIgnoreCase("Nfe")){
                   continue;
                } 
                try {
                    
                    NfeInformacao informacao = new NfeInformacao();
                    List<NfeDetalhe> colDetalhe = new ArrayList<NfeDetalhe>();
                    for (Element nfe : nefProc.getChildren()) {                     
                        //Só processo infNfe
                        if(!nfe.getName().equalsIgnoreCase("infNfe")){
                           continue; 
                        }
                        
                        for (Element infNef : nfe.getChildren()) {
                           
                           if(infNef.getName().equalsIgnoreCase("ide")){
                               NfeIdentificador identificador = new NfeIdentificador();
                               for(Element ide :infNef.getChildren()){
                                   if(ide.getName().equalsIgnoreCase("nNF")){
                                     identificador.setNroNotaFiscal(Long.parseLong(ide.getValue()));  
                                   }
                                   if(ide.getName().equalsIgnoreCase("dEmi")){
                                       identificador.setDataEmissao(JUtil.StringToDate(ide.getValue(), "yyyy-MM-dd"));
                                   } 
                               }
                               informacao.setIdentificador(identificador);
                           }
                           
                           if (infNef.getName().equalsIgnoreCase("emit")) {
                               NfeEmitente emitente = new NfeEmitente();
                               
                               for (Element emit : infNef.getChildren()) {
                                  if(emit.getName().equalsIgnoreCase("CNPJ")){
                                    emitente.setCnpj(emit.getValue());
                                  }  
                                  if(emit.getName().equalsIgnoreCase("xNome")){
                                    emitente.setNome(emit.getValue());
                                  }
                               }
                               informacao.setEmitente(emitente);
                           }
                           
                           if (infNef.getName().equalsIgnoreCase("det")) {
                               NfeDetalhe detalhe = new NfeDetalhe();  
                               for (Element det : infNef.getChildren()) {
                                  if(det.getName().equalsIgnoreCase("prod")) {
                                    NfeProduto produto = new NfeProduto();  
                                    for (Element prod : det.getChildren()) {
                                      if(prod.getName().equalsIgnoreCase("NCM")){
                                          produto.setNCM(Long.parseLong(prod.getValue()));
                                      }
                                      if(prod.getName().equalsIgnoreCase("CFOP")){
                                          produto.setCFOP(Long.parseLong(prod.getValue()));
                                      }
                                      if(prod.getName().equalsIgnoreCase("qTrib")){ 
                                         produto.setQtdItem(new BigDecimal(prod.getValue()));
                                      } 
                                      if(prod.getName().equalsIgnoreCase("vUnTrib")){ 
                                         produto.setValorPrd(new BigDecimal(prod.getValue()));
                                      } 
                                    }
                                    detalhe.setProduto(produto);
                                  }
                                  if(det.getName().equalsIgnoreCase("imposto")) {
                                    NfeImposto imposto = new NfeImposto();
                                    for (Element imp : det.getChildren()) {
                                        
                                        if(imp.getName().equalsIgnoreCase("vTotTrib")){
                                            imposto.setTotalTributo(new BigDecimal(imp.getValue()));
                                        }
                                        
                                        if(imp.getName().equalsIgnoreCase("ICMS")){
                                            for(Element icms : imp.getChildren()){
                                                if(icms.getName().contains("ICMS")){
                                                  for(Element icms60 : icms.getChildren()){
                                                     if(icms60.getName().equalsIgnoreCase("vICMSSTRet") ||
                                                        icms60.getName().equalsIgnoreCase("vICMS")){
                                                       imposto.setIcms(new BigDecimal(icms60.getValue()));
                                                     } 
                                                  }  
                                                }
                                            }
                                        }
                                        
                                        if(imp.getName().equalsIgnoreCase("II")){
                                            for(Element ii : imp.getChildren()){
                                                if(ii.getName().equalsIgnoreCase("vIOF")){
                                                    imposto.setIof(new BigDecimal(ii.getValue()));
                                                }
                                            }
                                        }

                                        if(imp.getName().equalsIgnoreCase("PIS")){
                                            for(Element pis : imp.getChildren()){
                                                if(pis.getName().contains("PIS")){
                                                  for(Element pisAliq : pis.getChildren()){
                                                     if(pisAliq.getName().equalsIgnoreCase("vPIS")){
                                                       imposto.setPis(new BigDecimal(pisAliq.getValue()));
                                                     } 
                                                  }  
                                                }
                                            }
                                        }
                                        
                                        
                                        if(imp.getName().equalsIgnoreCase("COFINS")){
                                            for(Element pis : imp.getChildren()){
                                                if(pis.getName().contains("COFINS")){
                                                  for(Element pisAliq : pis.getChildren()){
                                                     if(pisAliq.getName().equalsIgnoreCase("vCOFINS")){
                                                       imposto.setCofins(new BigDecimal(pisAliq.getValue()));
                                                     } 
                                                  }  
                                                }
                                            }
                                        }

                                        
                                        
                                    }
                                    detalhe.setImposto(imposto);
                                  }
                                  
                               }
                               colDetalhe.add(detalhe); 
                           }
                       }
                       
                       informacao.setDetalhes(colDetalhe);
                       notaFiscal.setInformacao(informacao);
                   } 
                   
                    colecaoNfe.add(notaFiscal);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    throw new Exception("Problema na estrutura do arquivo: "+file.getName()+"! Motivo: "+ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Exception("Erro ao processar o arquivo: "+file.getName()+"!\n Motivo: "+ex.getMessage());
                }
            }
        }
        System.out.println(colecaoNfe);
        return colecaoNfe;
    }
}
