package com.godev.atomus.entity.broker;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record BrokerRegisterData(

        @NotBlank
        String name,

        @NotBlank
        @CNPJ
        String cnpj
) {}
