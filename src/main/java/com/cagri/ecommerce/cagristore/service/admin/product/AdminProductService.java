package com.cagri.ecommerce.cagristore.service.admin.product;

import com.cagri.ecommerce.cagristore.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    List<ProductDto> getAllProductByName(String name);

    boolean deleteProduct(Long productId);

    ProductDto updateProduct(Long productId, ProductDto updatedProductDto);
}
