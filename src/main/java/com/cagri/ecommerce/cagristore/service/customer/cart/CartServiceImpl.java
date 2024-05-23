package com.cagri.ecommerce.cagristore.service.customer.cart;

import com.cagri.ecommerce.cagristore.dto.AddProductInCartDto;
import com.cagri.ecommerce.cagristore.dto.CartItemDto;
import com.cagri.ecommerce.cagristore.dto.OrderDto;
import com.cagri.ecommerce.cagristore.dto.PlacedOrderDto;
import com.cagri.ecommerce.cagristore.entity.CartItem;
import com.cagri.ecommerce.cagristore.entity.Order;
import com.cagri.ecommerce.cagristore.entity.Product;
import com.cagri.ecommerce.cagristore.entity.User;
import com.cagri.ecommerce.cagristore.repository.CartItemsRepository;
import com.cagri.ecommerce.cagristore.repository.OrderRepository;
import com.cagri.ecommerce.cagristore.repository.ProductRepository;
import com.cagri.ecommerce.cagristore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
        Order order = orderRepository.findByUserId(addProductInCartDto.getUserId());

        if (order == null) {
            order = new Order();
            order.setUser(userRepository.findById(addProductInCartDto.getUserId()).orElse(null));
            order.setTotalAmount(0.0);
            order.setOrderAmount(0.0);
            order.setCartItems(new ArrayList<>());
            order = orderRepository.save(order);
        }

        Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setPrice(product.getProductPrice());
            cartItem.setQuantity(1L);
            cartItem.setUser(order.getUser());
            cartItem.setOrder(order);

            CartItem savedCartItem = cartItemsRepository.save(cartItem);

            order.setTotalAmount(order.getTotalAmount() + savedCartItem.getPrice());
            order.setOrderAmount(order.getOrderAmount() + savedCartItem.getPrice());

            order.getCartItems().add(savedCartItem);

            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCartItem);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }


    }


    @Override
    @Transactional(readOnly = true)
    public OrderDto getCartByUserId(Long userId) {
        Order order = orderRepository.findByUserId(userId);
        List<CartItemDto> cartItemDtos = order.getCartItems().stream().map(CartItem::getCartItemDto).toList();

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderAmount(order.getOrderAmount());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setCartItems(cartItemDtos);
        return orderDto;
    }


    @Override
    public Order placeOrder(PlacedOrderDto placedOrderDto) {
        Order order = orderRepository.findByUserId(placedOrderDto.getUserId());
        Optional<User> optionalUser = userRepository.findById(placedOrderDto.getUserId());
        if (optionalUser.isPresent()) {
            order.setOrderDescription(placedOrderDto.getOrderDescription());
            order.setAddress(placedOrderDto.getAddress());
            order.setOrderDate(new Date());
            order = orderRepository.save(order);

            Order lastOrder = new Order();
            lastOrder.setOrderAmount(0.0);
            lastOrder.setTotalAmount(0.0);
            lastOrder.setUser(optionalUser.get());
            orderRepository.save(lastOrder);
            return order;
        }
        return null;
    }


}




