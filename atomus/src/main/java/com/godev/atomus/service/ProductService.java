package com.godev.atomus.service;

import com.godev.atomus.entity.product.Product;
import com.godev.atomus.entity.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void save(Product product) {
        repository.save(product);
    }

    public Product getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }
}
