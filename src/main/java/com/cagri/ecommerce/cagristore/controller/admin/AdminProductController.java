package com.cagri.ecommerce.cagristore.controller.admin;


import com.cagri.ecommerce.cagristore.dto.ProductDto;
import com.cagri.ecommerce.cagristore.service.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) {
        ProductDto productDto1 = adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }


    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);

    }


    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable String productName) {
        List<ProductDto> productDtos = adminProductService.getAllProductByName(productName);
        return ResponseEntity.ok(productDtos);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean deleted = adminProductService.deleteProduct(productId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) {
        ProductDto updatedProduct = adminProductService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

}
