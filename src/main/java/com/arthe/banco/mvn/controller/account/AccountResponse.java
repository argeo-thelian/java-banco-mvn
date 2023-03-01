package com.arthe.banco.mvn.controller.account;

import com.arthe.banco.mvn.utils.GenericResponse;
import com.arthe.banco.mvn.utils.UtilsGenericException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AccountResponse<T> extends UtilsGenericException<T> {

    protected ResponseEntity<GenericResponse<T>> response(GenericResponse<T> genericResponse){
        if (genericResponse != null){
            return new ResponseEntity<>
                    (genericResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>
                    (genericResponse, HttpStatus.CONFLICT);
        }
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<GenericResponse<T>>
    handleSeverException(Exception e){
        return getResponseEntityGeneric(e.getMessage());
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<GenericResponse<T>>
    handleSeverException(DataAccessException e){
        return getResponseEntityGeneric(e.getMessage());
    }


}
