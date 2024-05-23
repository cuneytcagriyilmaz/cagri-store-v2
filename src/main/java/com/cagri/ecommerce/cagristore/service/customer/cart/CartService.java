package com.cagri.ecommerce.cagristore.service.customer.cart;

import com.cagri.ecommerce.cagristore.dto.AddProductInCartDto;
import com.cagri.ecommerce.cagristore.dto.OrderDto;
import com.cagri.ecommerce.cagristore.dto.PlacedOrderDto;
import com.cagri.ecommerce.cagristore.entity.Order;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

//    Order increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    Order placeOrder(PlacedOrderDto placedOrderDto);


}
