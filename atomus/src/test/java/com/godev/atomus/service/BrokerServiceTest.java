package com.godev.atomus.service;

import com.godev.atomus.entity.broker.Broker;
import com.godev.atomus.entity.broker.BrokerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BrokerServiceTest {

    @Mock
    private BrokerRepository repository;

    @InjectMocks
    private BrokerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetReferenceById() {

        Broker broker = new Broker();
        broker.setId(1L);
        broker.setName("XP");

        when(repository.getReferenceById(1L)).thenReturn(broker);

        Broker retrievedBroker = service.getReferenceById(1L);

        assertEquals(broker, retrievedBroker);
    }
}