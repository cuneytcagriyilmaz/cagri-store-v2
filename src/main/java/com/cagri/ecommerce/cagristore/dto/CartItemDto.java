package com.cagri.ecommerce.cagristore.dto;


import lombok.Data;

@Data
public class CartItemDto {

    private Long cartItemId;

    private Double price;

    private Long quantity;

    private Long productId;

    private Long orderId;
    private String productName;

    private byte[] catItemImg;
    private Long userId;
}
