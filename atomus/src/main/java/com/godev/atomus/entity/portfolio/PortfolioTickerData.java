package com.godev.atomus.entity.portfolio;

import java.math.BigDecimal;

public record PortfolioTickerData(

        String title,
        BigDecimal totalAmount,
        int quantity,
        BigDecimal averagePrice,
        BigDecimal regularMarketPrice,
        BigDecimal profitability

) { }