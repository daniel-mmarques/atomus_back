package com.godev.atomus.entity.user;

import jakarta.validation.constraints.NotNull;

public record UserProfileEditData (

        @NotNull
        Long profileId

){ }