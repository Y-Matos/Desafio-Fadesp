package com.fadesp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Status não pode ser alterado")
public class ForbiddenUpdateException extends RuntimeException{
    
}
