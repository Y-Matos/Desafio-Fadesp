package com.fadesp.api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Código de Pagamento duplicado")
public class PagamentoDuplicadoException extends RuntimeException {

}
