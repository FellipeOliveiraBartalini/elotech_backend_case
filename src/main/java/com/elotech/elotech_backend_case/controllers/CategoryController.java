package com.elotech.elotech_backend_case.controllers;


import com.elotech.elotech_backend_case.models.CategoryModel;
import com.elotech.elotech_backend_case.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryModel> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryModel getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public CategoryModel createCategory(@RequestBody CategoryModel categoryModel) {
        return categoryService.save(categoryModel);
    }

    @PutMapping("/{id}")
    public CategoryModel updateCategory(@PathVariable Long id, @RequestBody CategoryModel categoryModel) {
        return categoryService.update(id, categoryModel);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        CategoryModel category = categoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryService.delete(category);
    }
}
