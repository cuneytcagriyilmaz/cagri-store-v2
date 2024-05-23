package com.cagri.ecommerce.cagristore.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class StoreGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StoreErrorResponse> handleException(StoreException storeException) {

        StoreErrorResponse storeErrorResponse = new StoreErrorResponse(storeException.getHttpStatus().value(), storeException.getLocalizedMessage(), System.currentTimeMillis());
        log.error("exception occurred ! Exception message = {}", storeException.getLocalizedMessage());
        return new ResponseEntity<>(storeErrorResponse, storeException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<StoreErrorResponse> handleException(Exception exception) {

        StoreErrorResponse storeErrorResponse = new StoreErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(storeErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
