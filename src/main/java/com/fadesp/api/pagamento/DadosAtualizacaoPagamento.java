package com.fadesp.api.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPagamento(
    @NotNull
    int paymentCode,
    
    @NotBlank
    String new_status
    
    ) {

}
