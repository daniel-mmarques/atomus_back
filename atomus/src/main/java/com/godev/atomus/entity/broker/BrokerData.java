package com.godev.atomus.entity.broker;

public record BrokerData(Long id, String name, String cnpj) {
    public BrokerData(Broker broker) {
        this(broker.getId(), broker.getName(), broker.getCnpj());
    }
}
