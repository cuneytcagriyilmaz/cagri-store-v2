package com.cagri.ecommerce.cagristore.repository;


import com.cagri.ecommerce.cagristore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CartItemsRepository extends JpaRepository<CartItem, Long> {


}
