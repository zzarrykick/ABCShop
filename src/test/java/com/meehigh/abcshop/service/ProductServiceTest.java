package com.meehigh.abcshop.service;

import com.meehigh.abcshop.dto.CategoryResponse;
import com.meehigh.abcshop.dto.ProductRequest;
import com.meehigh.abcshop.dto.ProductResponse;
import com.meehigh.abcshop.exception.CategoryNotFoundException;
import com.meehigh.abcshop.exception.ProductNotFoundException;
import com.meehigh.abcshop.model.Category;
import com.meehigh.abcshop.model.Product;
import com.meehigh.abcshop.repository.CategoryRepository;
import com.meehigh.abcshop.repository.ProductRepository;
import com.meehigh.abcshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;   // <-- înlocuiește linia cu `any`
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)   // spune lui JUnit să folosească Mockito
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;     // "DB" falsă pentru produse

    @Mock
    CategoryRepository categoryRepository;   // "DB" falsă pentru categorii

    @InjectMocks
    ProductService productService;           // instanță REALĂ, primește mock-urile de sus

    // ========== TEST 1: getAllProducts - listă goală ==========

    @Test
    void getAllProducts_whenRepositoryReturnsEmptyList_shouldThrowProductNotFoundException() {
        // GIVEN - repository întoarce listă goală
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        // WHEN + THEN - ne așteptăm la ProductNotFoundException
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getAllProducts()
        );

        assertTrue(ex.getMessage().contains("No products found"));

        // verify - a fost apelat findAll o singură dată
        verify(productRepository, times(1)).findAll();
    }

    // ========== TEST 2: getProductById - produs inexistent ==========

    @Test
    void getProductById_whenProductDoesNotExist_shouldThrowProductNotFoundException() {
        // GIVEN
        Long id = 123L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN + THEN
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(id)
        );

        assertTrue(ex.getMessage().contains("No product with id: " + id + " found"));

        verify(productRepository, times(1)).findById(id);
    }

    // ========== TEST 3: addProduct - categorie inexistentă ==========

    @Test
    void addProduct_whenCategoryDoesNotExist_shouldThrowCategoryNotFoundException() {
        // GIVEN - un ProductRequest cu o categorie care NU există în DB

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(99L);
        categoryResponse.setName("NonExistingCategory");

        ProductRequest request = new ProductRequest();
        request.setName("Produs");
        request.setDescription("Descriere");
        request.setCategory(categoryResponse);
        request.setThumbnailUrl("https://example.com/img.jpg");
        request.setPrice(BigDecimal.valueOf(100));
        request.setStock(10);

        // repository-ul de categorii întoarce Optional.empty() => nu există categoria
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        // WHEN + THEN - ne așteptăm la CategoryNotFoundException
        CategoryNotFoundException ex = assertThrows(
                CategoryNotFoundException.class,
                () -> productService.addProduct(request)
        );

        assertTrue(ex.getMessage().contains("Category with id 99 not found"));

        // foarte important: nu trebuie să se apeleze productRepository.save(...)
        verify(productRepository, never()).save(any(Product.class));
    }

    // ========== TEST 4: getProductByName - succes ==========

    @Test
    void getProductByName_whenProductsExist_shouldReturnListOfResponses() {
        // GIVEN
        String productName = "Telefon";

        // construim o entitate Product ca și cum ar veni din DB
        Product productEntity = new Product();
        productEntity.setId(1L);
        productEntity.setName(productName);
        productEntity.setDescription("Telefon smart");
        productEntity.setThumbnailUrl("https://example.com/img.jpg");
        productEntity.setPrice(BigDecimal.valueOf(1000));
        productEntity.setStock(5);

        Category category = new Category();
        category.setId(10L);
        category.setName("Electronics");
        productEntity.setCategory(category);

        // repository-ul întoarce o listă cu un produs
        when(productRepository.findByName(productName))
                .thenReturn(List.of(productEntity));

        // WHEN
        List<ProductResponse> result = productService.getProductByName(productName);

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());

        ProductResponse response = result.get(0);
        assertEquals(1L, response.getId());
        assertEquals("Telefon", response.getName());
        assertEquals("Telefon smart", response.getDescription());
        assertEquals(BigDecimal.valueOf(1000), response.getPrice());
        assertEquals(5, response.getStock());
        assertNotNull(response.getCategory());
        assertEquals(10L, response.getCategory().getId());
        assertEquals("Electronics", response.getCategory().getName());

        verify(productRepository, times(1)).findByName(productName);
    }

// ========== TEST 5: getProductByName - fără rezultate ==========

    @Test
    void getProductByName_whenNoProductsFound_shouldThrowProductNotFoundException() {
        // GIVEN
        String productName = "NoSuchProduct";

        when(productRepository.findByName(productName))
                .thenReturn(Collections.emptyList());

        // WHEN + THEN
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductByName(productName)
        );

        assertTrue(ex.getMessage().contains("No product with name: " + productName + " found"));

        verify(productRepository, times(1)).findByName(productName);
    }

    // ========== TEST 6: editProduct - succes (produs și categorie există) ==========

    @Test
    void editProduct_whenProductAndCategoryExist_shouldUpdateAndReturnResponse() {
        // GIVEN
        Long productId = 1L;

        // produsul deja existent în DB
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Vechi");
        existingProduct.setDescription("Descriere veche");
        existingProduct.setThumbnailUrl("https://old.img");
        existingProduct.setPrice(BigDecimal.valueOf(100));
        existingProduct.setStock(1);

        Category oldCategory = new Category();
        oldCategory.setId(10L);
        oldCategory.setName("OldCategory");
        existingProduct.setCategory(oldCategory);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(existingProduct));

        // noul request cu date actualizate
        CategoryResponse newCategoryResponse = new CategoryResponse();
        newCategoryResponse.setId(20L);
        newCategoryResponse.setName("NewCategory");

        ProductRequest request = new ProductRequest();
        request.setName("Nou");
        request.setDescription("Descriere nouă");
        request.setThumbnailUrl("https://new.img");
        request.setPrice(BigDecimal.valueOf(200));
        request.setStock(5);
        request.setCategory(newCategoryResponse);

        // categoria nouă există în DB
        Category newCategory = new Category();
        newCategory.setId(20L);
        newCategory.setName("NewCategory");

        when(categoryRepository.findById(20L))
                .thenReturn(Optional.of(newCategory));

        // la salvare, repository-ul întoarce produsul modificat
        when(productRepository.save(existingProduct))
                .thenReturn(existingProduct);

        // WHEN
        ProductResponse result = productService.editProduct(productId, request);

        // THEN
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Nou", result.getName());
        assertEquals("Descriere nouă", result.getDescription());
        assertEquals("https://new.img", result.getThumbnailUrl());
        assertEquals(BigDecimal.valueOf(200), result.getPrice());
        assertEquals(5, result.getStock());
        assertNotNull(result.getCategory());
        assertEquals(20L, result.getCategory().getId());
        assertEquals("NewCategory", result.getCategory().getName());

        verify(productRepository, times(1)).findById(productId);
        verify(categoryRepository, times(1)).findById(20L);
        verify(productRepository, times(1)).save(existingProduct);
    }

// ========== TEST 7: editProduct - produs inexistent ==========

    @Test
    void editProduct_whenProductDoesNotExist_shouldThrowProductNotFoundException() {
        // GIVEN
        Long productId = 999L;
        ProductRequest request = new ProductRequest();
        request.setName("Orice");

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> productService.editProduct(productId, request)
        );

        assertTrue(ex.getMessage().contains("Product with id " + productId + " not found"));

        verify(productRepository, times(1)).findById(productId);
        // nu trebuie să se apeleze categoryRepository și save
        verify(categoryRepository, never()).findById(anyLong());
        verify(productRepository, never()).save(any(Product.class));
    }

// ========== TEST 8: editProduct - categorie inexistentă ==========

    @Test
    void editProduct_whenNewCategoryDoesNotExist_shouldThrowCategoryNotFoundException() {
        // GIVEN
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Vechi");
        when(productRepository.findById(productId))
                .thenReturn(Optional.of(existingProduct));

        CategoryResponse newCategoryResponse = new CategoryResponse();
        newCategoryResponse.setId(99L);
        newCategoryResponse.setName("NonExistingCategory");

        ProductRequest request = new ProductRequest();
        request.setName("Nou");
        request.setCategory(newCategoryResponse);

        // categoria 99 NU există
        when(categoryRepository.findById(99L))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        CategoryNotFoundException ex = assertThrows(
                CategoryNotFoundException.class,
                () -> productService.editProduct(productId, request)
        );

        assertTrue(ex.getMessage().contains("Category with id 99 not found"));

        verify(productRepository, times(1)).findById(productId);
        verify(categoryRepository, times(1)).findById(99L);
        // dacă nu găsește categoria, nu trebuie să facă save
        verify(productRepository, never()).save(any(Product.class));
    }

    // ========== TEST 9: deleteProduct - succes ==========

    @Test
    void deleteProduct_whenProductExists_shouldDeleteIt() {
        // GIVEN
        Long productId = 1L;

        Product existingProduct = new Product();
        existingProduct.setId(productId);

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(existingProduct));

        // WHEN
        productService.deleteProduct(productId);

        // THEN
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(existingProduct);
    }

// ========== TEST 10: deleteProduct - produs inexistent ==========

    @Test
    void deleteProduct_whenProductDoesNotExist_shouldThrowProductNotFoundException() {
        // GIVEN
        Long productId = 999L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // WHEN + THEN
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> productService.deleteProduct(productId)
        );

        assertTrue(ex.getMessage().contains("Product with id: " + productId + " not found"));

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).delete(any(Product.class));
    }


}

