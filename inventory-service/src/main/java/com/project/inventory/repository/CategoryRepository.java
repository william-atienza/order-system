package com.project.inventory.repository;

import com.project.inventory.dto.CategoryRecord;
import com.project.inventory.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("""
            SELECT new com.project.inventory.dto.CategoryRecord(c.id, c.name,
              (SELECT new com.project.inventory.dto.SubCategoryRecord(s.id, s.name , null) 
               FROM SubCategory s WHERE s.category = c ))
            FROM Category c
            """)
    public List<CategoryRecord> getAll();

    @Query("""
            SELECT new com.project.inventory.dto.CategoryRecord(c.id, c.name,
              (SELECT new com.project.inventory.dto.SubCategoryRecord(s.id, s.name , null)
               FROM SubCategory s WHERE s.category = c ))
            FROM Category c WHERE c.id = :categoryId
            """)
    public Optional<CategoryRecord> getByIdParam(@Param("categoryId") Long categoryId);

    public Optional<CategoryRecord> findByNameContaining(String name);
}
