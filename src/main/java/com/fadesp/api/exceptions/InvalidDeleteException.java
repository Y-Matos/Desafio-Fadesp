package com.fadesp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Pagamento não pode ser deletado pois seu Status não é pendente")
public class InvalidDeleteException extends RuntimeException{

}
