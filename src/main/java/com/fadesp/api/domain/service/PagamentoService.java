package com.fadesp.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fadesp.api.domain.exception.DeleteInvalidoException;
import com.fadesp.api.domain.exception.PagamentoDuplicadoException;
import com.fadesp.api.domain.exception.PagamentoNaoEncontradoException;
import com.fadesp.api.domain.model.AtualizacaoPagamentoDTO;
import com.fadesp.api.domain.model.ListagemPagamentoDTO;
import com.fadesp.api.domain.model.Pagamento;
import com.fadesp.api.domain.model.PagamentoDTO;
import com.fadesp.api.domain.model.Status;
import com.fadesp.api.domain.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento cadastrarPagamento(PagamentoDTO dados) {
        Optional<Pagamento> pagamentoExistente = repository.findById(dados.codigoPagamento());
        if (pagamentoExistente.isPresent()) {
            throw new PagamentoDuplicadoException();
        } else {
            Pagamento novoPagamento = new Pagamento(dados);
            return repository.save(novoPagamento);
        }
    }

    public List<ListagemPagamentoDTO> listarPagamentos() {
        return repository.findAll().stream().map(ListagemPagamentoDTO::new).toList();
    }

    public ListagemPagamentoDTO listarPagamentoPorCodigoPagamento(int codigoPagamento) {
        return repository.findById(codigoPagamento).map(ListagemPagamentoDTO::new).orElseThrow(PagamentoNaoEncontradoException::new);
    }

    public List<ListagemPagamentoDTO> listarPagamentosPorDocumento(String documento) {
        return repository.findAllByDocumento(documento);
    }

    public List<ListagemPagamentoDTO> listarPagamentosPorStatus(String status) {
        return repository.findAllByStatus(Status.valueOf(status));
    }

    @Transactional
    public void atualizarStatusPagamento(AtualizacaoPagamentoDTO dados) {
        int codigoPagamento = dados.codigoPagamento();
        Pagamento pagamento = repository.findById(codigoPagamento).orElseThrow(PagamentoNaoEncontradoException::new);
        pagamento.atualizarStatus(dados);
    }

    @Transactional
    public void deletarPagamento(int codigoPagamento) {
        Pagamento pagamento = repository.findById(codigoPagamento).orElseThrow(PagamentoNaoEncontradoException::new);

        if (pagamento.getStatus() == Status.processamento_pendente) {
            repository.deleteById(codigoPagamento);
        } else {
            throw new DeleteInvalidoException();
        }
    }
}