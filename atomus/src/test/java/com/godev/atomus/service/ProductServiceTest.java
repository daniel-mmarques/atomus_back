package com.godev.atomus.service;

import com.godev.atomus.entity.product.Product;
import com.godev.atomus.entity.product.ProductRepository;
import com.godev.atomus.entity.product.ProductResgisterData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetReferenceById() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(repository.getReferenceById(1L)).thenReturn(product);

        Product retrievedProduct = service.getReferenceById(1L);

        assertEquals(product, retrievedProduct);
    }

    @Test
    void testFindAll() {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product(new ProductResgisterData("stock", false)));
        productList.add(new Product(new ProductResgisterData("fund", false)));

        when(repository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = service.findAll();

        assertEquals(productList, retrievedProducts);
    }
}
