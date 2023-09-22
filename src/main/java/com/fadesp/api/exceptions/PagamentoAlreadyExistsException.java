package com.fadesp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Código de Pagamento duplicado")
public class PagamentoAlreadyExistsException extends RuntimeException {

}
