package br.com.atom.api_nfce.dtos;

import lombok.Data;

@Data
public class NfceResponseDTO {

    private String statusSefaz;  //ex: "100" Autorização uso da NFC-e
    private String motivo;
    private String chaveAcesso;
    private String protocoloAutorizacao;
    private String xmlAutorizado;

}
