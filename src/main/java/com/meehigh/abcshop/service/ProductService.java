package com.meehigh.abcshop.service;

import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
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

    public Product getProductById(Long id) {
        if(productRepository.existsById(id)){
            return productRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public void addNewOrder(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void editProduct(Long id,  Product product) {
        if(productRepository.existsById(id)) {
            productRepository.save(product);
        }
    }

    @Transactional
    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
