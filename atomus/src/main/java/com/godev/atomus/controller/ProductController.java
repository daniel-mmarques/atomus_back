package com.godev.atomus.controller;

import com.godev.atomus.entity.product.Product;
import com.godev.atomus.entity.product.ProductData;
import com.godev.atomus.entity.product.ProductResgisterData;
import com.godev.atomus.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@Valid @RequestBody ProductResgisterData productResgisterData, UriComponentsBuilder uriComponentsBuilder) {
        Product product = new Product(productResgisterData);
        productService.save(product);
        var uri = uriComponentsBuilder.path("/produto/{productId}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductData(product));
    }

    @GetMapping("/{productId}")
    public ResponseEntity detalhar(@PathVariable Long productId) {
        return ResponseEntity.ok(new ProductData(productService.getReferenceById(productId)));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> Listar() {
        return ResponseEntity.ok(productService.findAll());
    }
}
