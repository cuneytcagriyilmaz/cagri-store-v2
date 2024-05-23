package com.cagri.ecommerce.cagristore.repository;


import com.cagri.ecommerce.cagristore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
