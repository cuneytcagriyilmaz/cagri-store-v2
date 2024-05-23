package com.cagri.ecommerce.cagristore.repository;

import com.cagri.ecommerce.cagristore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    Order findByUserId(Long userId);

}
