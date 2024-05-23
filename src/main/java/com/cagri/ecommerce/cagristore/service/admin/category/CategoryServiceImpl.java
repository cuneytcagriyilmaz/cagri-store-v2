package com.cagri.ecommerce.cagristore.service.admin.category;


import com.cagri.ecommerce.cagristore.dto.CategoryDto;
import com.cagri.ecommerce.cagristore.entity.Category;
import com.cagri.ecommerce.cagristore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
