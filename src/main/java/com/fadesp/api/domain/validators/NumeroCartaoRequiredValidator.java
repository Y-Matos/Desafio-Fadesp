package com.fadesp.api.domain.validators;

import com.fadesp.api.domain.model.Pagamento;
import com.fadesp.api.domain.model.MetodoPagamento;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumeroCartaoRequiredValidator implements ConstraintValidator<NumeroCartaoRequired, Pagamento> {

    @Override
    public void initialize(NumeroCartaoRequired constraintAnnotation) {
    }

    @Override
    public boolean isValid(Pagamento pagamento, ConstraintValidatorContext context) {
        MetodoPagamento metodoPagamento = pagamento.getMetodoPagamento();
        
        // Testa se o método escolhido for cartão e só valida caso o número do cartão não seja nulo
        if (metodoPagamento == MetodoPagamento.cartao_credito || metodoPagamento == MetodoPagamento.cartao_debito) {
            return pagamento.getNumeroCartao() != null;
        } else if (metodoPagamento == MetodoPagamento.boleto || metodoPagamento == MetodoPagamento.pix) {
            return pagamento.getNumeroCartao() == null;
        }
    
        // Caso nenhum dos métodos anteriores corresponda, a validação falha
        return false;
    }
}
