package com.fadesp.api.domain.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String mensagem, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("mensagem", mensagem);
            map.put("HTTP Status", status.value());
            map.put("dados", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
}