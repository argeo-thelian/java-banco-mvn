package com.arthe.banco.mvn.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class UtilsGenericException<T> {

    public ResponseEntity<GenericResponse<T>> getResponseEntityGeneric(String message) {
        String messageException = message != null ? message : "DB Error";
        Map<String, String> errors = new HashMap<>();
        errors.put(DetailTransaction.ERROR_DESCRIPTION, "Error en Base de Datos");
        errors.put(DetailTransaction.ERROR_BUSINESS_MEANING, "[BD exception]: " + messageException);
        return getGenericResponseEntityException(messageException, errors);
    }

    public ResponseEntity<GenericResponse<T>>
    getGenericResponseEntityException(String messageException, Map<String, String> errors) {
        return new ResponseEntity<>(
                new GenericResponse<>(
                        null,
                        null,
                        "Error en Base de Datos.",
                        messageException,
                        false,
                        new DetailTransaction(
                                -1,
                                200,
                                2,
                                "Error en Base de Datos",
                                errors
                        )
                ), HttpStatus.PARTIAL_CONTENT
        );
    }
}
