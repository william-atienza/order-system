package com.project.inventory.dto;

import com.project.inventory.entity.Category;
import com.project.inventory.entity.SubCategory;

public record ProductRecord(Long id, String name, String description, double price, int quantity, String imageUrl,
                            Category category, SubCategory subCategory) {
}
