package com.godev.atomus.client.brapi.ticker;

public record TickerDetails(
        String symbol,
        String longName,
        long marketCap,
        double regularMarketPrice,
        double regularMarketChange,
        double regularMarketChangePercent,
        double twoHundredDayAverage,
        double twoHundredDayAverageChange,
        double twoHundredDayAverageChangePercent,
        double regularMarketDayHigh,
        double regularMarketDayLow,
        long regularMarketVolume,
        double earningsPerShare,
        String logourl,
        String regularMarketTime
) {}
