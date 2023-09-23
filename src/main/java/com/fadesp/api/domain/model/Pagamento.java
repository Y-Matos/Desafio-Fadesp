package com.fadesp.api.domain.model;

import java.math.BigDecimal;

import com.fadesp.api.domain.exception.UpdateProibidoException;
import com.fadesp.api.domain.validators.NumeroCartaoRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Table(name = "pagamentos")
@Entity(name = "Pagamento")
@NumeroCartaoRequired(message = "O número do cartão é obrigatório para pagamento com cartão de crédito.")
public class Pagamento {

    @Id
    private int codigoPagamento;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String documento;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    private String numeroCartao;

    private BigDecimal valorPagamento;

    public Pagamento(int codigoPagamento, Status status, String documento, MetodoPagamento metodoPagamento, String numeroCartao,
            BigDecimal valorPagamento) {
        this.codigoPagamento = codigoPagamento;
        this.status = status;
        this.documento = documento;
        this.metodoPagamento = metodoPagamento;
        this.numeroCartao = numeroCartao;
        this.valorPagamento = valorPagamento;
    }

    public Pagamento() {
    }

    public int getcodigoPagamento() {
        return codigoPagamento;
    }

    public void setcodigoPagamento(int codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigoPagamento;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
        result = prime * result + ((metodoPagamento == null) ? 0 : metodoPagamento.hashCode());
        result = prime * result + ((numeroCartao == null) ? 0 : numeroCartao.hashCode());
        result = prime * result + ((valorPagamento == null) ? 0 : valorPagamento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagamento other = (Pagamento) obj;
        if (codigoPagamento != other.codigoPagamento)
            return false;
        if (status != other.status)
            return false;
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        if (metodoPagamento != other.metodoPagamento)
            return false;
        if (numeroCartao == null) {
            if (other.numeroCartao != null)
                return false;
        } else if (!numeroCartao.equals(other.numeroCartao))
            return false;
        if (valorPagamento == null) {
            if (other.valorPagamento != null)
                return false;
        } else if (!valorPagamento.equals(other.valorPagamento))
            return false;
        return true;
    }

    public Pagamento(PagamentoDTO dados) {
        this.codigoPagamento = dados.codigoPagamento();
        this.status = Status.processamento_pendente;
        this.documento = dados.documento();
        this.metodoPagamento = dados.metodoPagamento();
        this.numeroCartao = dados.numeroCartao();
        this.valorPagamento = dados.valorPagamento();
    }

    public void atualizarStatus(@Valid AtualizacaoPagamentoDTO dados) {
        Status novoStatus = Status.valueOf(dados.novoStatus());

        if (this.status == Status.processado_sucesso){
            throw new UpdateProibidoException();
        } else if (this.status == Status.processamento_pendente){
            this.status = novoStatus;
        } else {
            if (status == Status.processamento_pendente){
                this.status = novoStatus;
            } else {
                throw new UpdateProibidoException();
            }
        }
    }

}
