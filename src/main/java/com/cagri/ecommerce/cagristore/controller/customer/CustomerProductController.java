package com.cagri.ecommerce.cagristore.controller.customer;


import com.cagri.ecommerce.cagristore.dto.ProductDto;
import com.cagri.ecommerce.cagristore.service.customer.product.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts() throws IOException {
        List<ProductDto> productDtos = customerProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);

    }


    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable String productName) {
        List<ProductDto> productDtos = customerProductService.getAllProductByName(productName);
        return ResponseEntity.ok(productDtos);
    }


}
