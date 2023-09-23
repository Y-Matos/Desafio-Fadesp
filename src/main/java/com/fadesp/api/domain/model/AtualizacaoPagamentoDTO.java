package com.fadesp.api.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoPagamentoDTO(
    @NotNull
    int codigoPagamento,
    
    @NotBlank
    String novoStatus
    
    ) {

}
