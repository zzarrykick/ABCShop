package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.dto.ProductRequest;
import com.meehigh.abcshop.dto.ProductResponse;
import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.exception.CategoryNotFoundException;
import com.meehigh.abcshop.exception.ProductNotFoundException;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.repository.CategoryRepository;
import com.meehigh.abcshop.repository.ProductRepository;
import com.meehigh.abcshop.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> Utils.productEntityToResponse(product))
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id)
                .map( product ->  Utils.productEntityToResponse(product))
                .orElseThrow(() -> new AddressNotFoundException("Product with id: " + id + " not found"));
    }

    public List<ProductResponse> getProductByName(String productName) {
        List<ProductResponse> productResponses = productRepository.findByName(productName).stream()
                .map(product -> Utils.productEntityToResponse(product)).collect(Collectors.toList());
        if(!productResponses.isEmpty()){
            throw (new AddressNotFoundException("Address with name: " + productName + " not found"));
        }
        return productResponses;
    }

    @Transactional
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = Utils.productRequestToEntity(productRequest);
        Category category = categoryRepository.findById(productRequest.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + productRequest.getCategory().getId() + " not found"));

        product.setCategory(category);
        return Utils.productEntityToResponse((productRepository.save(product)));

        //return Utils.productEntityToResponse(productRepository.save(Utils.productRequestToEntity((productRequest))));
    }

    @Transactional
    public ProductResponse editProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setThumbnailUrl(productRequest.getThumbnailUrl());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());

        if (productRequest.getCategory() != null && productRequest.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category with id " + productRequest.getCategory().getId() + " not found"));
            existingProduct.setCategory(category);
        }

        return Utils.productEntityToResponse(productRepository.save(existingProduct));
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found"));
        productRepository.delete(product);
    }

}
