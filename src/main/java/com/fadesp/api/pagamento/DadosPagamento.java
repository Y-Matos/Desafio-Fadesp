package com.fadesp.api.pagamento;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record DadosPagamento(
    @NotNull
    int paymentCode,

    Status status,
    
    @NotBlank(message = "O campo de documento não pode estar vazio.")
    @Pattern(regexp = "\\d{11,14}", message = "o documento não possui o tamanho correto")
    String document,
    
    @NotNull
    PaymentMethod paymentMethod,
    
    @Pattern(regexp = "\\d{1,16}", message = "o número do cartão não pode ser vazio")
    String cardNumber,
    
    @Positive(message = "Valor informado é inválido")
    BigDecimal paymentValue

    ) {
}
