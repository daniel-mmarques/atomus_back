package com.godev.atomus.entity.portfolio;

import java.math.BigDecimal;
import java.util.List;

public record PortfolioData(

        Long userId,
        BigDecimal totalAmount,
        List<PortfolioProductData> portfolioProductData

) { }