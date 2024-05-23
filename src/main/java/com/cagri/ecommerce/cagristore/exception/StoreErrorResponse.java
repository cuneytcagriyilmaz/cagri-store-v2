package com.cagri.ecommerce.cagristore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreErrorResponse {
    private Integer status;
    private String message;
    private Long timestamp;
}
