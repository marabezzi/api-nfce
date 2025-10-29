package br.com.atom.api_nfce.sefaz;

import org.springframework.stereotype.Component;

import br.com.atom.api_nfce.enuns.Ambiente;
import lombok.Data;

@Component
public class SefazClient {

    // Overload 1: aceita enum (recomendado pelo type-safety)
    public ResultadoAutorizacao autorizar(String xmlAssinado, String uf, Ambiente ambiente) {
        return autorizar(xmlAssinado, uf, ambiente.name()); // delega para Overload 2
    }

    // Overload 2: aceita String ("PROD"/"HOM") — compatibilidade
    public ResultadoAutorizacao autorizar(String xmlAssinado, String uf, String ambiente) {

        boolean producao = "PROD".equalsIgnoreCase(ambiente);
        String tpAmb = producao ? "1" : "2"; // 1=Produção, 2=Homologação

        // TODO:
        // 1) Descobrir endpoint correto por UF+ambiente (NFeAutorizacao4)
        // 2) Montar <enviNFe> com idLote e incluir xmlAssinado
        // 3) SOAP via HTTPS (mTLS usando SSLContext do CertificadoConfig)
        // 4) Ler cStat, nProt, chNFe e montar XML autorizado (NFe + protNFe)

        // MOCK de retorno para fechar o ciclo compilável:
        ResultadoAutorizacao r = new ResultadoAutorizacao();
        r.setCStat("100");
        r.setMotivo("Autorizado o uso da NFC-e (mock)");
        r.setChaveAcesso("CHAVE-DE-EXEMPLO");
        r.setProtocolo("PROTOCOLO-EXEMPLO");
        r.setXmlAutorizado(xmlAssinado /* + <protNFe>... */);
        return r;
    }

    @Data
    public static class ResultadoAutorizacao {
        private String cStat;
        private String motivo;
        private String chaveAcesso;
        private String protocolo;
        private String xmlAutorizado;
    }
}