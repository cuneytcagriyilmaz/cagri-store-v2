package com.cagri.ecommerce.cagristore.service.customer.product;


import com.cagri.ecommerce.cagristore.dto.ProductDto;

import java.util.List;


public interface CustomerProductService {

    List<ProductDto> getAllProductByName(String name);

    List<ProductDto> getAllProducts();
}
