package com.cagri.ecommerce.cagristore.service.customer.product;

import com.cagri.ecommerce.cagristore.dto.ProductDto;
import com.cagri.ecommerce.cagristore.entity.Product;
import com.cagri.ecommerce.cagristore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

//    public List<ProductDto> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().map(Product::getProductDto).collect(Collectors.toList());
//    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getProductDto).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByProductNameContainingIgnoreCase(name);
        return products.stream().map(Product::getProductDto).collect(Collectors.toList());
    }

}
