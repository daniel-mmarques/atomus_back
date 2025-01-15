package com.godev.atomus.entity.portfolio;

import java.math.BigDecimal;
import java.util.List;

public record PortfolioProductData(

        String product,
        BigDecimal totalAmount,
        List<PortfolioTickerData> portfolioTickerData

) { }