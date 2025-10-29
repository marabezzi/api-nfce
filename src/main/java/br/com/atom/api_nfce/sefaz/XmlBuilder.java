package br.com.atom.api_nfce.sefaz;

import org.springframework.stereotype.Component;

import br.com.atom.api_nfce.dtos.NfceRequestDTO;

@Component
public class XmlBuilder {

    public String montarXmlNfce(NfceRequestDTO dto) {
        // TODO: Implementar layout completo 4.00 (modelo 65), gerar chave de acesso, etc.
        // Placeholder para manter o fluxo compilável:
        String tpAmb = dto.getAmbiente() == null ? "2" : ("PROD".equals(dto.getAmbiente().name()) ? "1" : "2");
        return
            "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">" +
              "<infNFe versao=\"4.00\" Id=\"NFeFAKECHAVE\">" +
                "<ide>" +
                  "<cUF>35</cUF>" +
                  "<mod>65</mod>" +
                  "<serie>" + dto.getSerie() + "</serie>" +
                  "<nNF>" + dto.getNumeroNota() + "</nNF>" +
                  "<tpAmb>" + tpAmb + "</tpAmb>" +
                  "<finNFe>1</finNFe>" +
                  "<indFinal>1</indFinal>" +
                  "<indPres>1</indPres>" +
                "</ide>" +
                "<emit>" +
                  "<CNPJ>" + dto.getCnpjEmitente() + "</CNPJ>" +
                  "<IE>" + dto.getIeEmitente() + "</IE>" +
                  "<xNome>" + dto.getRazaoSocial() + "</xNome>" +
                "</emit>" +
                // TODO: det(itens), total, pag (detPag), infAdic, QRCode, etc.
              "</infNFe>" +
            "</NFe>";
    }
}