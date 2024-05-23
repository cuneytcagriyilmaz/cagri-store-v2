package com.cagri.ecommerce.cagristore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "order", schema = "cagristorev2")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_description")
    private String orderDescription;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_amount")
    private Double orderAmount;

    @Column(name = "address")
    private String address;

    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "Id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();


}
