package com.godev.atomus.entity.trade;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record TradeEditData(

        @NotNull
        Long id,

        @NotNull
        @PastOrPresent
        LocalDate tradeDate,

        @NotNull
        Long brokerId,

        @NotNull
        @Min(1)
        Integer quantity,

        @NotNull
        @Min(0)
        Double unitPrice,

        @NotNull
        @Min(0)
        Double fees
) {}
