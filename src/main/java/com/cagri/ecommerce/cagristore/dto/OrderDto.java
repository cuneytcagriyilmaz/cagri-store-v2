package com.cagri.ecommerce.cagristore.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;

    private String orderDescription;

    private Date orderDate;

    private Double orderAmount;

    private String address;

    private String payment;


    private Double totalAmount;

    private String userName;

    private List<CartItemDto> cartItems = new ArrayList<>();
}
