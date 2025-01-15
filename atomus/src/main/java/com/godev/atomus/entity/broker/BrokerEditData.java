package com.godev.atomus.entity.broker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record BrokerEditData(

    @NotNull
    Long id,

    @NotBlank
    String name,

    @NotBlank
    @CNPJ
    String cnpj
) {}
