package com.godev.atomus.controller;

import com.godev.atomus.entity.broker.Broker;
import com.godev.atomus.entity.broker.BrokerData;
import com.godev.atomus.entity.broker.BrokerEditData;
import com.godev.atomus.entity.broker.BrokerRegisterData;
import com.godev.atomus.service.BrokerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/corretora")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@Valid @RequestBody BrokerRegisterData brokerRegisterData, UriComponentsBuilder uriComponentsBuilder) {
        Broker broker = new Broker(brokerRegisterData);
        brokerService.save(broker);
        var uri = uriComponentsBuilder.path("/corretora/{id}").buildAndExpand(broker.getId()).toUri();
        return ResponseEntity.created(uri).body(new BrokerData(broker));
    }

    @GetMapping("/{brokerId}")
    public ResponseEntity detalhar(@PathVariable Long brokerId) {
        return ResponseEntity.ok(new BrokerData(brokerService.getReferenceById(brokerId)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editar (@Valid @RequestBody BrokerEditData corretoraDadosEdicao) {
        brokerService.getReferenceById(corretoraDadosEdicao.id()).editBroker(corretoraDadosEdicao);
        return ResponseEntity.ok().build();
    }
}
