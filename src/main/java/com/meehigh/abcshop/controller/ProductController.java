package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.CategoryResponse;
import com.meehigh.abcshop.dto.ProductRequest;
import com.meehigh.abcshop.dto.ProductResponse;
import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/getbyname/{productName}")
    public ResponseEntity<List<ProductResponse>> getProductByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.getProductByName(productName));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable long id, @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.editProduct(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFound(ProductNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}
