package com.fadesp.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.fadesp.api.exceptions.InvalidDeleteException;
import com.fadesp.api.exceptions.PagamentoAlreadyExistsException;
import com.fadesp.api.exceptions.PagamentoNotFoundException;
import com.fadesp.api.pagamento.DadosAtualizacaoPagamento;
import com.fadesp.api.pagamento.DadosPagamento;
import com.fadesp.api.pagamento.Pagamento;
import com.fadesp.api.pagamento.PaymentRepository;
import com.fadesp.api.pagamento.Status;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PaymentController {
    
    @Autowired
    private PaymentRepository repository;

    // Cadastra um novo pagamento caso não exista um pagamento com o mesmo código.
    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosPagamento dados ) {
        Optional<Pagamento> pagamentoExistente = repository.findById(dados.paymentCode());
        if (pagamentoExistente.isPresent()){
            throw new PagamentoAlreadyExistsException();
        } else{
            repository.save(new Pagamento(dados));
            return ResponseEntity.status(HttpStatus.CREATED).body("Pagamento cadastrado com sucesso");
        }
    }

    @GetMapping
    public List<Pagamento> listar(){
        return repository.findAll();
    }

    @GetMapping("/paymentCode/{code}")
    public ResponseEntity<Pagamento> listarPorCode(@PathVariable int code ){
        Pagamento pagamento = repository.findById(code).orElseThrow(PagamentoNotFoundException::new);
        return ResponseEntity.ok(pagamento);
    }

    @GetMapping("/document/{document}")
    public List<Pagamento> listarPorDocument(@PathVariable String document ){
        return repository.findAllByDocument(document);
    }

    @GetMapping("/status/{status}")
    public List<Pagamento> listarPorStatus(@PathVariable String status ){
        return repository.findAllByStatus(Status.valueOf(status));
        
    }

    // Atualiza o status de um pagamento no banco através do Id e um novo Status
    @Transactional
    @PutMapping
    public ResponseEntity<String> atualizarStatus(@RequestBody @Valid DadosAtualizacaoPagamento dados){
        int paymentCode = dados.paymentCode();
        Pagamento pagamento = repository.findById(paymentCode).orElseThrow(PagamentoNotFoundException::new);
        pagamento.atualizarStatus(dados);
        return ResponseEntity.ok("Status atualizado com sucesso");
    }

    // Deleta um pagamento do banco através do Id (paymentCode)
    @DeleteMapping("/paymentCode/{code}")
    @Transactional
    public ResponseEntity<String> deletarPagamento(@PathVariable int code ){
        Pagamento pagamento = repository.findById(code).orElseThrow(PagamentoNotFoundException::new);
        
        if (pagamento.getStatus() == Status.process_pending){
            repository.deleteById(code);
            return ResponseEntity.ok("Pagamento excluído com sucesso");
        } else {
            throw new InvalidDeleteException();
        }

    }

}
