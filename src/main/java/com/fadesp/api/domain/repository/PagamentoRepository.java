package com.fadesp.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fadesp.api.domain.model.ListagemPagamentoDTO;
import com.fadesp.api.domain.model.Pagamento;
import com.fadesp.api.domain.model.Status;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    List<ListagemPagamentoDTO> findAllByDocumento(String documento);

    List<ListagemPagamentoDTO> findAllByStatus(Status statusPagamento);

}
