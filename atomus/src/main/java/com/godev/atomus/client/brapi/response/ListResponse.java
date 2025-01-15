package com.godev.atomus.client.brapi.response;

import com.godev.atomus.client.brapi.ticker.IndexList;
import com.godev.atomus.client.brapi.ticker.TickerList;

import java.util.List;

public record ListResponse(
        List<IndexList> indexes,
        List<TickerList> stocks,
        List<String> availableSectors,
        List<String> availableStockTypes,
        int currentPage,
        int totalPages,
        int itemsPerPage,
        int totalCount,
        boolean hasNextPage
) { }
