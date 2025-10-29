package br.com.atom.api_nfce.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
@Data 
public class NfceRequestDTO {

    //Dados do emitente
    private String cnpjEmitente;
    private String ieEmitente;
    private String razaoSocial;
    private String nomeFantasia;

    //Identificação da NFC-e
    private Integer numeroNota;
    private Integer serie;
    private String ambiente;

    //Itens
    private List<ItemVenda> itens;

    //Pagamento (ex. dinheiro, cartão, pix)
    private List<FormaPagamento> pagamentos;


    public static class ItemVenda {
        private String descricao;
        private String ncm;
        private String cfop;
        private BigDecimal quantidade;
        private BigDecimal valorUnitario;
        private BigDecimal aliquotaIcms;
    }

    public static class FormaPagamento{
        private String tipo;
        private BigDecimal valor;
    }
}
