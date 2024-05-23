package com.cagri.ecommerce.cagristore.service.admin.product;

import com.cagri.ecommerce.cagristore.dto.ProductDto;
import com.cagri.ecommerce.cagristore.entity.Category;
import com.cagri.ecommerce.cagristore.entity.Product;
import com.cagri.ecommerce.cagristore.exception.StoreException;
import com.cagri.ecommerce.cagristore.repository.CategoryRepository;
import com.cagri.ecommerce.cagristore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

//    public ProductDto addProduct(ProductDto productDto) {
//        Product product = new Product();
//        product.setProductName(productDto.getProductName());
//        product.setProductDescription(productDto.getProductDescription());
//        product.setProductPrice(productDto.getProductPrice());
//
//        if (productDto.getProductByteImage() != null) {
//            byte[] imageBytes = Base64.getDecoder().decode(productDto.getProductByteImage());
//            product.setProductImage(imageBytes);
//        }
//
//        Category category = categoryRepository.findById(productDto.getCategoryId())
//                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
//
//        product.setCategory(category);
//        return productRepository.save(product).getProductDto();
//    }
//
//    public List<ProductDto> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        return products.stream().map(Product::getProductDto).collect(Collectors.toList());
//    }

    public ProductDto addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());

        if (productDto.getProductByteImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(productDto.getProductByteImage());
            product.setProductImage(imageBytes);
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new StoreException("Category not found ID: " + productDto.getCategoryId(), HttpStatus.NOT_FOUND));

        product.setCategory(category);
        return productRepository.save(product).getProductDto();
    }

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

//
//    public boolean deleteProduct(Long productId) {
//        Optional<Product> productOptional = productRepository.findById(productId);
//        if (productOptional.isPresent()) {
//            productRepository.deleteById(productId);
//            return true;
//        } else {
//            return false;
//        }
//
//    }


    public boolean deleteProduct(Long productId) {
        Optional<Product> productOptional = Optional.ofNullable(productRepository.findById(productId).orElseThrow(() -> new StoreException("Product not found ID: " + productId, HttpStatus.NOT_FOUND)));
        if (productOptional.isPresent()) {
            productRepository.deleteById(productId);
            return true;
        } else {
            return false;
        }

    }


//    public ProductDto updateProduct(Long id, ProductDto productDto) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
//
//        product.setProductName(productDto.getProductName());
//        product.setProductDescription(productDto.getProductDescription());
//        product.setProductPrice(productDto.getProductPrice());
//
//        if (productDto.getProductByteImage() != null) {
//            byte[] imageBytes = Base64.getDecoder().decode(productDto.getProductByteImage());
//            product.setProductImage(imageBytes);
//        }
//
//        Category category = categoryRepository.findById(productDto.getCategoryId())
//                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
//
//        product.setCategory(category);
//        return productRepository.save(product).getProductDto();
//    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new StoreException("Product not found ID:" + id, HttpStatus.NOT_FOUND));

        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setProductPrice(productDto.getProductPrice());

        if (productDto.getProductByteImage() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(productDto.getProductByteImage());
            product.setProductImage(imageBytes);
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new StoreException("Category not found ID: " + productDto.getCategoryId(), HttpStatus.NOT_FOUND));

        product.setCategory(category);
        return productRepository.save(product).getProductDto();
    }

}



