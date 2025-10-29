package br.com.atom.api_nfce.sefaz;

import org.springframework.stereotype.Component;

@Component
public class XmlSigner {

    // TODO: injetar PrivateKey e X509Certificate (CertificadoConfig)
    // e assinar a tag <infNFe> com XMLDSig (ICP-Brasil)

    public String assinar(String xmlSemAssinatura) {
        // Placeholder: retornar o mesmo XML até implementar a assinatura
        return xmlSemAssinatura;
    }
}