package com.project.inventory.service;

import com.project.inventory.dto.CategoryRecord;
import com.project.inventory.dto.SubCategoryRecord;
import com.project.inventory.entity.Category;
import com.project.inventory.exception.CategoryException;
import com.project.inventory.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    final CategoryRepository repository;

    CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    @Cacheable(value = "categoryCache", key = "#categoryRecord.name")
    public CategoryRecord save(CategoryRecord categoryRecord){
        Category category = repository.save(new Category(categoryRecord.name(), null));
        return new CategoryRecord(category.getId(), category.getName(), null);
    }

    @CacheEvict(value = "categoryCache", key = "#categoryRecord.name")
    public void delete(CategoryRecord categoryRecord){
        Category category = repository.findById(categoryRecord.id()).orElseThrow(() -> new CategoryException("Category not found!"));
        repository.delete(category);
    }

    public List<CategoryRecord> getCategories(){
        return repository.getAll();
    }

    @Cacheable(value = "categoryCache", key = "#id")
    public CategoryRecord getCategoryById(Long id){
        return repository.getByIdParam(id).orElseThrow(() -> new CategoryException("Category not found!"));
    }

    @Cacheable(value = "categoryCache", key = "#name")
    public CategoryRecord getCategoryByName(String name){
        return repository.findByNameContaining(name).orElseThrow(() -> new CategoryException("Category not found!"));
    }

    public List<SubCategoryRecord> getSubCategoriesByCategoryId(Long categoryId){
        Category category = repository.findById(categoryId).orElseThrow(() -> new CategoryException("Category not found!"));
        return category.getSubCategories().stream()
                .map(subCategory -> new SubCategoryRecord(subCategory.getId(), subCategory.getName(), null))
                .toList();
    }
}
