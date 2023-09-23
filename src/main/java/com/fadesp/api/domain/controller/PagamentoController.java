package com.fadesp.api.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fadesp.api.domain.model.AtualizacaoPagamentoDTO;
import com.fadesp.api.domain.model.ListagemPagamentoDTO;
import com.fadesp.api.domain.model.PagamentoDTO;
import com.fadesp.api.domain.response.ResponseHandler;
import com.fadesp.api.domain.service.PagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    // Cadastra um novo pagamento caso não exista um pagamento com o mesmo código.
    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid PagamentoDTO dados) {
        pagamentoService.cadastrarPagamento(dados);
        return ResponseHandler.generateResponse("Pagamento cadastrado com sucesso.", HttpStatus.CREATED, null);
    }

    @GetMapping
    public ResponseEntity<Object> listar() {
        List<ListagemPagamentoDTO> result = pagamentoService.listarPagamentos();
        return ResponseHandler.generateResponse("Pagamentos listados com sucesso!", HttpStatus.OK, result);
    }

    @GetMapping("/codigoPagamento/{codigoPagamento}")
    public ResponseEntity<Object> listarPorCodigoPagamento(@PathVariable int codigoPagamento) {
        ListagemPagamentoDTO pagamento = pagamentoService.listarPagamentoPorCodigoPagamento(codigoPagamento);
        return ResponseHandler.generateResponse("Pagamento encontrado com sucesso!", HttpStatus.OK, pagamento);
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<Object> listarPorDocument(@PathVariable String documento) {
        List<ListagemPagamentoDTO> result = pagamentoService.listarPagamentosPorDocumento(documento);
        return ResponseHandler.generateResponse("Pagamentos listados com sucesso!", HttpStatus.OK, result);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Object> listarPorStatus(@PathVariable String status) {
        List<ListagemPagamentoDTO> result = pagamentoService.listarPagamentosPorStatus(status);
        return ResponseHandler.generateResponse("Pagamentos listados com sucesso!", HttpStatus.OK, result);
    }

    // Atualiza o status de um pagamento no banco através do Id e um novo Status
    @Transactional
    @PutMapping
    public ResponseEntity<Object> atualizarStatus(@RequestBody @Valid AtualizacaoPagamentoDTO dados) {
        pagamentoService.atualizarStatusPagamento(dados);
        return ResponseHandler.generateResponse("Status atualizado com sucesso", HttpStatus.OK, null);
    }

    // Deleta um pagamento do banco através do Id (codigoPagamento)
    @DeleteMapping("/codigoPagamento/{codigoPagamento}")
    @Transactional
    public ResponseEntity<Object> deletarPagamento(@PathVariable int codigoPagamento) {
        pagamentoService.deletarPagamento(codigoPagamento);
        return ResponseHandler.generateResponse("Pagamento excluído com sucesso", HttpStatus.OK, null);
    }
}
