package com.godev.atomus.controller;

import com.godev.atomus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/{ticker}")
    public ResponseEntity searchTicker(@PathVariable String ticker) {
        return ResponseEntity.ok(searchService.searchDetails(ticker));
    }

    @GetMapping("/list/all")
    public ResponseEntity searchAllList() {
        return ResponseEntity.ok(searchService.searchList());
    }

    @GetMapping("/list/{type}")
    public ResponseEntity searchAllList(@PathVariable String type) {
        return ResponseEntity.ok(searchService.searchListByType(type));
    }

    @GetMapping("/available/{ticker}")
    public ResponseEntity searchAvailable(@PathVariable String ticker) {
        return ResponseEntity.ok(searchService.searchAvailable(ticker));
    }
}

