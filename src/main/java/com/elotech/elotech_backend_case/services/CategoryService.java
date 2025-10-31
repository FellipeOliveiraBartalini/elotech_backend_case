package com.elotech.elotech_backend_case.services;

import com.elotech.elotech_backend_case.models.CategoryModel;
import com.elotech.elotech_backend_case.repositories.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel save(@Valid CategoryModel categoryModel) {
        return categoryRepository.save(categoryModel);
    }

    public CategoryModel update(Long id, @Valid CategoryModel newCategoryModel) {
        CategoryModel existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingCategory.setName(newCategoryModel.getName());
        return categoryRepository.save(existingCategory);
    }

    public void delete(CategoryModel categoryModel) {
        categoryRepository.deleteById(categoryModel.getId());
    }
}
