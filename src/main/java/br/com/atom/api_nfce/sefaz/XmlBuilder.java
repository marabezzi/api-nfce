package br.com.atom.api_nfce.sefaz;

import org.springframework.stereotype.Component;

import br.com.atom.api_nfce.dtos.NfceRequestDTO;

@Component
public class XmlBuilder {

    public String montarXmlNfce(NfceRequestDTO dto){

         // IMPORTANTE:
        // - Aqui você deve montar exatamente o layout "NFe" (modelo 65), com infNFe, ide, emit, dest (pode ser consumidor final sem CPF/CNPJ), det(itens), total, pag, infAdic etc.
        // - Também precisa gerar a chave de acesso (cUF + data + CNPJ + modelo 65 + série + número + tpEmis + cNF + DV)

        // Abaixo é só um placeholder simplificado para amarrar o fluxo:
        return "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
          "<infNFe versao=\"4.00\" Id=\"NFeFAKECHAVE\">" +
          "<ide>"+
          "<cUF>35</cUF>"+
          "<mod>65</mod>"+
          "<serie>" + dto.getSerie() + "</serie>"+
          "<nNF>" + dto.getNumeroNota() + "</nNF>"+
          "<tpAmb>" + ("PROD".equals(dto.getAmbiente()) ? "1" : "2") + "/tpAmb>" +
          "<finNFe>1</finNFe>"+             //Operaçao normal
          "<indFinal>1</indFinal>"+         //Consumidor final
          "<indPres>1</indPres>"+           //Operação presencial
          "</ide>"+
          "<emit>" +
                  "<CNPJ>" + dto.getCnpjEmitente() + "</CNPJ>" +
                  "<IE>" + dto.getIeEmitente() + "</IE>" +
                  "<xNome>" + dto.getRazaoSocial() + "</xNome>" +
                "</emit>" +
                // ... det (itens), total, pag, etc ...
              "</infNFe>" +
            "</NFe>";
    }  
}
