package com.project.inventory.dto;

import java.util.List;

public record CategoryRecord(Long id, String name, List<SubCategoryRecord> subCategories) {
}
