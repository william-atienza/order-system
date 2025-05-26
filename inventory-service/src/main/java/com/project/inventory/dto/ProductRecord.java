package com.project.inventory.dto;

import com.project.inventory.entity.Category;
import com.project.inventory.entity.SubCategory;

import java.math.BigDecimal;

public record ProductRecord(Long id, String name, String description, BigDecimal price, int quantity, String imageUrl,
                            Category category, SubCategory subCategory) {
}
