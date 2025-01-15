package com.godev.atomus.entity.product;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "product")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isCoveredFgc;

    public Product(ProductResgisterData productResgisterData) {
        this.name = productResgisterData.name();
        this.isCoveredFgc = productResgisterData.isCoveredFgc();
    }
}
