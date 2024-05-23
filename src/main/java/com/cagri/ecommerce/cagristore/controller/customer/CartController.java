package com.cagri.ecommerce.cagristore.controller.customer;


import com.cagri.ecommerce.cagristore.dto.AddProductInCartDto;
import com.cagri.ecommerce.cagristore.dto.OrderDto;
import com.cagri.ecommerce.cagristore.dto.PlacedOrderDto;
import com.cagri.ecommerce.cagristore.service.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


    @PostMapping("/placedOrder")
    public ResponseEntity<?> addProductToCart(@RequestBody PlacedOrderDto placedOrderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placedOrderDto));
    }


}
