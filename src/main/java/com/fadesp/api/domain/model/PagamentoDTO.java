package com.fadesp.api.domain.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record PagamentoDTO(
    @Positive(message = "É necessário o código do pagamento.")
    int codigoPagamento,
    
    @NotBlank(message = "O campo de documento não pode estar vazio.")
    @Pattern(regexp = "\\d{11,14}", message = "O documento não possui o tamanho correto.")
    String documento,
    
    @NotNull(message = "É necessário o método do pagamento.")
    MetodoPagamento metodoPagamento,
    
    @Pattern(regexp = "\\d{1,16}", message = "O número do cartão não pode ser vazio.")
    String numeroCartao,
    
    @Positive(message = "Valor informado deve ser maior que 0")
    BigDecimal valorPagamento

    ) {
}
