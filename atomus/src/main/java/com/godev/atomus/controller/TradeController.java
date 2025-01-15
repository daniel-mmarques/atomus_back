package com.godev.atomus.controller;

import com.godev.atomus.entity.trade.Trade;
import com.godev.atomus.entity.trade.TradeData;
import com.godev.atomus.entity.trade.TradeEditData;
import com.godev.atomus.entity.trade.TradeRegisterData;
import com.godev.atomus.service.TradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/negociacao")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@Valid @RequestBody TradeRegisterData tradeRegisterData, UriComponentsBuilder uriComponentsBuilder) {
        Trade trade = new Trade(tradeRegisterData);
        tradeService.save(trade);
        var uri = uriComponentsBuilder.path("/negociacao/{id}").buildAndExpand(trade.getId()).toUri();
        return ResponseEntity.created(uri).body(new TradeData(trade));
    }

    @GetMapping("/detalhar/{id}")
    @Transactional
    public ResponseEntity detalhar (@PathVariable Long id) {
        return ResponseEntity.ok(new TradeData(tradeService.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar (@Valid @RequestBody TradeEditData tradeEditData) {
        tradeService.getReferenceById(tradeEditData.id()).editTrade(tradeEditData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar (@PathVariable Long id) {
        tradeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Trade>> fullHistory(@PathVariable Long id, @PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(tradeService.findAllByUsuarioId(id, pagination));
    }

    @GetMapping("/{id}/{product}")
    public ResponseEntity<List<Trade>> historyByProduct(@PathVariable Long id, @PathVariable String product, @PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(tradeService.findAllByUsuarioIdAndProduct(id, product, pagination));
    }

    @GetMapping("/{id}/{product}/{title}")
    public ResponseEntity<List<Trade>> historyByTitle(@PathVariable Long id, @PathVariable String product, @PathVariable String title, @PageableDefault(size = 10) Pageable pagination) {
        return ResponseEntity.ok(tradeService.findAllByUsuarioIdAndTitle(id, product, title, pagination));
    }
}
