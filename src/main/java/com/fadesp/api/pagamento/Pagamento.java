package com.fadesp.api.pagamento;

import java.math.BigDecimal;

import com.fadesp.api.exceptions.ForbiddenUpdateException;
import com.fadesp.api.validators.CardNumberRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pagamentos")
@Entity(name = "Pagamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "paymentCode")
@CardNumberRequired
public class Pagamento {
    
    @Id
    private int paymentCode;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String document;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String cardNumber;

    private BigDecimal paymentValue;
    
    public Pagamento(DadosPagamento dados) {
        this.paymentCode = dados.paymentCode();
        this.status = Status.process_pending;
        this.document = dados.document();
        this.paymentMethod = dados.paymentMethod();
        this.cardNumber = dados.cardNumber();
        this.paymentValue = dados.paymentValue();
    }

    public void atualizarStatus(@Valid DadosAtualizacaoPagamento dados) {
        Status newStatus = Status.valueOf(dados.new_status());

        if (this.status == Status.processed_success){
            throw new ForbiddenUpdateException();
        } else if (this.status == Status.process_pending){
            this.status = newStatus;
        } else {
            if (status == Status.process_pending){
                this.status = newStatus;
            } else {
                throw new ForbiddenUpdateException();
            }
        }
    }

}
