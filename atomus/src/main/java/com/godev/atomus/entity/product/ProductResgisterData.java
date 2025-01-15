package com.godev.atomus.entity.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResgisterData(

    @NotBlank
    String name,

    @NotNull
    Boolean isCoveredFgc

) {}

