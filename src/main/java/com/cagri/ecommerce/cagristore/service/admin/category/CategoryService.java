package com.cagri.ecommerce.cagristore.service.admin.category;


import com.cagri.ecommerce.cagristore.dto.CategoryDto;
import com.cagri.ecommerce.cagristore.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryDto categoryDto);

    List<Category> getAllCategories();
}
