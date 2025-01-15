package com.godev.atomus.entity.broker;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "broker")
@Entity(name = "Broker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Broker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;

    public Broker(BrokerRegisterData brokerRegisterData) {
        this.name = brokerRegisterData.name();
        this.cnpj = brokerRegisterData.cnpj();
    }

    public void editBroker(BrokerEditData brokerEditData) {
        this.name = brokerEditData.name();
        this.cnpj = brokerEditData.cnpj();
    }
}
