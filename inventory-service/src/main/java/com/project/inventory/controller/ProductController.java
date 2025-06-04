package com.project.inventory.controller;

import com.project.inventory.dto.ProductRecord;
import com.project.inventory.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    final ProductService service;
    ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRecord> save(@RequestBody ProductRecord productRecord){
        return ResponseEntity.ok(service.save(productRecord));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRecord> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductRecord> getByName(@PathVariable String name){
        return ResponseEntity.ok(service.getProduct(name));
    }

    @GetMapping
    public ResponseEntity<Page<ProductRecord>> getAll(@RequestParam("page") int page,
                                                      @RequestParam("size") int size,
                                                      @RequestParam("sortBy") String sortBy,
                                                      @RequestParam("sortDir") String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.ok(service.getProducts(pageable));
    }
}
