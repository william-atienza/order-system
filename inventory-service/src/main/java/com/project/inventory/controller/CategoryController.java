package com.project.inventory.controller;

import com.project.inventory.dto.CategoryRecord;
import com.project.inventory.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    final CategoryService service;

    CategoryController(CategoryService service){
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryRecord> save(@RequestBody CategoryRecord categoryRecord){
        return ResponseEntity.ok(service.save(categoryRecord));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestBody CategoryRecord categoryRecord){
        service.delete(categoryRecord);
        return ResponseEntity.ok("Category is deleted successfully!");
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryRecord> getByName(@PathVariable String name){
        return ResponseEntity.ok(service.getCategoryByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRecord> getByName(@PathVariable Long id){
        return ResponseEntity.ok(service.getCategoryById(id));
    }
}
