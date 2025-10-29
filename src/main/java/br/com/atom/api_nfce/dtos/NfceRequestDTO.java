package br.com.atom.api_nfce.dtos;

import java.math.BigDecimal;
import java.util.List;

import br.com.atom.api_nfce.enuns.Ambiente;
import lombok.Data;
@Data
public class NfceRequestDTO {

    // Emitente
    private String cnpjEmitente;
    private String ieEmitente;
    private String uf;              // "SP", "PR", etc.
    private String razaoSocial;
    private String nomeFantasia;

    // Identificação
    private Integer numeroNota;
    private Integer serie;
    private Ambiente ambiente;      // PROD ou HOM

    // Itens & Pagamentos
    private List<ItemVenda> itens;
    private List<FormaPagamento> pagamentos;

    @Data
    public static class ItemVenda {
        private String descricao;
        private String ncm;
        private String cfop;
        private BigDecimal quantidade;
        private BigDecimal valorUnitario;
        private BigDecimal aliquotaIcms;
    }

    @Data
    public static class FormaPagamento {
        private String tipo;        // "01","03","04","17", etc.
        private BigDecimal valor;
    }
}