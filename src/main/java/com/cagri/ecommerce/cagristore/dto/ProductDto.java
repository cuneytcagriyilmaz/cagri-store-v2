package com.cagri.ecommerce.cagristore.dto;


import lombok.Data;


@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private String productByteImage;
    private Long categoryId;


}

