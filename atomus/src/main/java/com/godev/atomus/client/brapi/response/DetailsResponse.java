package com.godev.atomus.client.brapi.response;

import com.godev.atomus.client.brapi.ticker.TickerDetails;

import java.util.List;

public record DetailsResponse(
        List<TickerDetails> results,
        String requestedAt,
        String took
) {}