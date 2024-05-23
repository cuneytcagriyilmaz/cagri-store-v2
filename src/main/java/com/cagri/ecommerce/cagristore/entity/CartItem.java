package com.cagri.ecommerce.cagristore.entity;


import com.cagri.ecommerce.cagristore.dto.CartItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "cart_item", schema = "cagristorev2")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long carItemId;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public CartItemDto getCartItemDto() {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartItemId(carItemId);
        cartItemDto.setPrice(price);
        cartItemDto.setProductId(product.getProductId());
        cartItemDto.setQuantity(quantity);
        cartItemDto.setUserId(user.getId());
        cartItemDto.setProductName(product.getProductName());
        cartItemDto.setCatItemImg(product.getProductImage());
        return cartItemDto;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "orderItemId=" + carItemId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", product=" + product +
                ", user=" + user +
                ", order=" + order +
                '}';
    }
}
