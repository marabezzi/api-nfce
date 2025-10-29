package br.com.atom.api_nfce.dtos;

import lombok.Data;

@Data
public class NfceResponseDTO {
    private String statusSefaz;            // ex.: "100"
    private String motivo;                 // ex.: "Autorizado o uso da NFC-e"
    private String chaveAcesso;
    private String protocoloAutorizacao;
    private String xmlAutorizado;          // XML final (NFe + protNFe)
    private String ambiente;               // opcional: "PROD" ou "HOM"
}