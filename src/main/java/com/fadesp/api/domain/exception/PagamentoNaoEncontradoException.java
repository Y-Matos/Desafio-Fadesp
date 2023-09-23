package com.fadesp.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "pagamento não encontrado")
public class PagamentoNaoEncontradoException extends RuntimeException{
    
}
