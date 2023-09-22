package com.fadesp.api.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

// Cria a Constraint para ser usada na classe de pagamento

@Constraint(validatedBy = CardNumberRequiredValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumberRequired  {

    String message() default "O número do cartão é obrigatório para pagamento com cartão de crédito.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}