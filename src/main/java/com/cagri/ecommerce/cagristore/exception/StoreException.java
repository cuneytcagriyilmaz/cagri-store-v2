package com.cagri.ecommerce.cagristore.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class StoreException extends RuntimeException {
    private HttpStatus httpStatus;

    public StoreException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
