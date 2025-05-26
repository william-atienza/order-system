package com.project.inventory.service;

import com.project.inventory.dto.ProductRecord;
import com.project.inventory.entity.Category;
import com.project.inventory.entity.Product;
import com.project.inventory.entity.SubCategory;
import com.project.inventory.exception.ProductException;
import com.project.inventory.repository.ProductPageableRepository;
import com.project.inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final ProductRepository repository;
    final ProductPageableRepository productPageableRepository;

    ProductService(ProductRepository repository, ProductPageableRepository productPageableRepository){
        this.repository = repository;
        this.productPageableRepository = productPageableRepository;
    }

    public ProductRecord getProductById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ProductException("Product not found!"));
        return mapEntityToRecord(product);
    }
    public ProductRecord getProduct(String name){
        return repository.findByNameContaining(name).orElseThrow(() -> new ProductException("Product not found!"));
    }
    public Page<ProductRecord> getProducts(Pageable pageable){
        return productPageableRepository.getAll(pageable);
    }

    public ProductRecord save(ProductRecord productRecord){
        try{
            Product product = new Product(productRecord.name(), productRecord.description(), productRecord.price(),
                    productRecord.quantity(), productRecord.imageUrl(), productRecord.category(), productRecord.subCategory(), 0);
            repository.save(product);
            return mapEntityToRecord(product);
        }catch (Exception e){
            throw new ProductException("Error encountered while saving the product!");
        }
    }

    public void delete(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ProductException("Product not found!"));
        repository.delete(product);
    }

    private ProductRecord mapEntityToRecord(Product product){
        return new ProductRecord(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity(), product.getImageUrl(), product.getCategory(), product.getSubCategory());
    }
}
