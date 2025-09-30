package com.meehigh.abcshop.service;

import com.meehigh.abcshop.exception.CategoryNotFoundException;
import com.meehigh.abcshop.exception.ProductNotFoundException;
import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " +id + " not found"));
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public ResponseEntity<String> editProduct(Long id, Product updatedProduct){
        return productRepository.findById(id).map(product -> {
            updatedProduct.setId(product.getId());
            productRepository.save(updatedProduct);
            return ResponseEntity.status(HttpStatus.OK).body("Product with id: " +id+ " has been updated successfully");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id: " + id + " not found"));
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        return productRepository.findById(id).map(product ->  {
            productRepository.deleteById(product.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Product has been deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with id: " +id+ " not found"));
    }


    // TODO
    public void validateCategoryForProduct(Product product) {

    }
}
