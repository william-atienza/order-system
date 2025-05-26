package com.project.inventory.repository;

import com.project.inventory.dto.ProductRecord;
import com.project.inventory.entity.Category;
import com.project.inventory.entity.Product;
import com.project.inventory.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPageableRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("Select new com.project.inventory.dto.ProductRecord(id, name, description, price, quantity, imageUrl, category, subCategory) FROM Product")
    public Page<ProductRecord> getAll(Pageable pageable);
    public List<ProductRecord> findByCategory(Category category, Pageable pageable);
    public List<ProductRecord> findBySubCategory(SubCategory subCategory, Pageable pageable);
}
