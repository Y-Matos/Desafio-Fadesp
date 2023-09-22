package com.fadesp.api.pagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Pagamento, Integer> {

    List<Pagamento> findAllByDocument(String document);

    List<Pagamento> findAllByStatus(Status paymentStatus);

}
