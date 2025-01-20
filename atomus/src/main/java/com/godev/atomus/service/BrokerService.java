package com.godev.atomus.service;

import com.godev.atomus.entity.broker.Broker;
import com.godev.atomus.entity.broker.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrokerService {

    @Autowired
    private BrokerRepository repository;

    public void save(Broker broker) {
        repository.save(broker);
    }

    public Broker getReferenceById(Long id) {
        return repository.getReferenceById(id);
    }

    public List<Broker> findAll() {
        return repository.findAll();
    }
}
