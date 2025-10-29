package br.com.atom.api_nfce.services;

import org.springframework.stereotype.Service;

import br.com.atom.api_nfce.dtos.NfceRequestDTO;
import br.com.atom.api_nfce.dtos.NfceResponseDTO;
import br.com.atom.api_nfce.sefaz.SefazClient;
import br.com.atom.api_nfce.sefaz.XmlBuilder;
import br.com.atom.api_nfce.sefaz.XmlSigner;

@Service
public class NfceService {

    private final XmlBuilder xmlBuilder;
    private final XmlSigner xmlSigner;
    private final SefazClient sefazClient;

    public NfceService(XmlBuilder xmlBuilder,
                       XmlSigner xmSigner,
                       SefazClient sefazClient) {
        this.xmlBuilder = xmlBuilder;
        this.xmlSigner = xmSigner;
        this.sefazClient = sefazClient;
    }

    public NfceResponseDTO autorizarNfce(NfceRequestDTO pedido){

        // 1. Monta XML NFC-e (Sem assinatura)
        String nfceXml = xmlBuilder.montarXmlNfce(pedido);
        
        // 2. Assinar XML (tag infNFce)
        String xmlAssinado = xmlSigner.assinar(nfceXml);

        // 3) Enviar para SEFAZ (aceita enum direto; overload cuida disso)
        SefazClient.ResultadoAutorizacao resultado =
                sefazClient.autorizar(xmlAssinado, pedido.getUf(), pedido.getAmbiente());

        // 4) Montar resposta
        NfceResponseDTO resp = new NfceResponseDTO();
        resp.setStatusSefaz(resultado.getCStat());
        resp.setMotivo(resultado.getMotivo());
        resp.setChaveAcesso(resultado.getChaveAcesso());
        resp.setProtocoloAutorizacao(resultado.getProtocolo());
        resp.setXmlAutorizado(resultado.getXmlAutorizado());
        resp.setAmbiente(pedido.getAmbiente().name());
        return resp;
    }
}