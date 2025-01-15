package com.godev.atomus.service;

import com.godev.atomus.entity.trade.Trade;
import com.godev.atomus.entity.trade.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository repository;

    @Autowired
    private SearchService searchService;

    public ResponseEntity save(Trade trade) {
        if (valid(trade.getTitle())) {
            repository.save(trade);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public boolean valid(String ticker) {
        return (!searchService.searchAvailable(ticker).stocks().isEmpty());
    }

    public void delete(Long id) {
        repository.delete(getReferenceById(id));
    }

    public Trade getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Trade> findAllByUsuarioId(Long id, Pageable pagination) {
        return repository.findAllByUsuarioId(id, pagination);
    }
    public List<Trade> findAllByUsuarioId(Long id) {
        return repository.findAllByUsuarioId(id, null);
    }

    public List<Trade> findAllByUsuarioIdAndProduct(Long id, String product, Pageable pagination) {
        return repository.findAllByUsuarioIdAndProduct(id, product, pagination);
    }
    public List<Trade> findAllByUsuarioIdAndProduct(Long id, String product) {
        return repository.findAllByUsuarioIdAndProduct(id, product, null);
    }

    public List<Trade> findAllByUsuarioIdAndTitle(Long id, String product, String title, Pageable pagination) {
        return repository.findAllByUsuarioIdAndTitle(id, product, title, pagination);
    }
    public List<Trade> findAllByUsuarioIdAndTitle(Long id, String product, String title) {
        return repository.findAllByUsuarioIdAndTitle(id, product, title, null);
    }

    public List<String> findProductsByUsuarioId(Long userId) {
        return repository.findProductsByUserId(userId);
    }
}
