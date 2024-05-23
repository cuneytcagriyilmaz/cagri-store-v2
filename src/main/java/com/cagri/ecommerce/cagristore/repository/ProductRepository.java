package com.cagri.ecommerce.cagristore.repository;

import com.cagri.ecommerce.cagristore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findAllByProductNameContainingIgnoreCase(String name);
}
