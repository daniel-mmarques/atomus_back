package com.godev.atomus.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserEditData(

        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        Long profileId
) { }