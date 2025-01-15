package com.godev.atomus.client.brapi.ticker;

public record TickerList(
        String stock,
        String name,
        double close,
        double change,
        long volume,
        double marketCap,
        String logo,
        String sector,
        String type
) { }
