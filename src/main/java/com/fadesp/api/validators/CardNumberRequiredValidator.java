package com.fadesp.api.validators;

import com.fadesp.api.pagamento.Pagamento;
import com.fadesp.api.pagamento.PaymentMethod;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CardNumberRequiredValidator implements ConstraintValidator<CardNumberRequired, Pagamento> {

    @Override
    public void initialize(CardNumberRequired constraintAnnotation) {
    }

    @Override
    public boolean isValid(Pagamento pagamento, ConstraintValidatorContext context) {
        PaymentMethod metodoPagamento = pagamento.getPaymentMethod();
        
        // Testa se o método escolhido for cartão e só valida caso o número do cartão não seja nulo
        if (metodoPagamento == PaymentMethod.cartao_credito || metodoPagamento == PaymentMethod.cartao_debito) {
            return pagamento.getCardNumber() != null;
        } else if (metodoPagamento == PaymentMethod.boleto || metodoPagamento == PaymentMethod.pix) {
            return pagamento.getCardNumber() == null;
        }
    
        // Caso nenhum dos métodos anteriores corresponda, a validação falha
        return false;
    }
}
