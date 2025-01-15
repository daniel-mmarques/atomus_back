package com.godev.atomus.controller;

import com.godev.atomus.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity fullPostfolio (@PathVariable Long id) {
        return ResponseEntity.ok(portfolioService.getPortfolio(id));
    }

    @GetMapping("/{id}/{product}")
    public ResponseEntity portfoliobyProduct (@PathVariable Long id, @PathVariable String product) {
        return ResponseEntity.ok(portfolioService.getPortfolioByProduct(id, product));
    }

    @GetMapping("/{id}/{product}/{title}")
    public ResponseEntity portifolioByTitle (@PathVariable Long id, @PathVariable String product, @PathVariable String title) {
        return ResponseEntity.ok(portfolioService.getPortfolioByTitle(id, product, title));
    }
}
