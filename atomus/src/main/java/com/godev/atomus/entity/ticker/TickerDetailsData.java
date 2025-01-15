package com.godev.atomus.entity.ticker;

import com.godev.atomus.client.brapi.response.DetailsResponse;

public record TickerDetailsData(

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

) {

    public TickerDetailsData(DetailsResponse detailsResponse) {
        this(
                detailsResponse.results().get(0).symbol(),
                detailsResponse.results().get(0).longName(),
                detailsResponse.results().get(0).marketCap(),
                detailsResponse.results().get(0).regularMarketPrice(),
                detailsResponse.results().get(0).regularMarketChange(),
                detailsResponse.results().get(0).regularMarketChangePercent(),
                detailsResponse.results().get(0).twoHundredDayAverage(),
                detailsResponse.results().get(0).twoHundredDayAverageChange(),
                detailsResponse.results().get(0).twoHundredDayAverageChangePercent(),
                detailsResponse.results().get(0).regularMarketDayHigh(),
                detailsResponse.results().get(0).regularMarketDayLow(),
                detailsResponse.results().get(0).regularMarketVolume(),
                detailsResponse.results().get(0).earningsPerShare(),
                detailsResponse.results().get(0).logourl(),
                detailsResponse.results().get(0).regularMarketTime()
        );
    }
}