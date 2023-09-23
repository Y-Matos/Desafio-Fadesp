package com.fadesp.api.domain.model;

import java.math.BigDecimal;


public record ListagemPagamentoDTO(
    int codigoPagamento,
    Status status,
    String documento,
    MetodoPagamento metodoPagamento,
    String numeroCartao,
    BigDecimal valorPagamento
) {

    public ListagemPagamentoDTO(Pagamento pagamento){
        this(pagamento.getcodigoPagamento(), pagamento.getStatus(), pagamento.getDocumento(), pagamento.getMetodoPagamento(), pagamento.getNumeroCartao(), pagamento.getValorPagamento());
    }
    
}
