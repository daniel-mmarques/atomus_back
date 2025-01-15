package com.godev.atomus.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserRegisterData(

    @NotBlank
    String name,

    @NotBlank
    @CPF
    String cpf,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min=8)
    String password,

    Long profileId,

    Boolean isActive

) {}
