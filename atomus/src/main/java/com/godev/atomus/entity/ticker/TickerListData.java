package com.godev.atomus.entity.ticker;

import com.godev.atomus.client.brapi.response.ListResponse;
import com.godev.atomus.client.brapi.ticker.IndexList;
import com.godev.atomus.client.brapi.ticker.TickerList;

import java.util.List;

public record TickerListData(

        List<TickerList> stocks,
        List<IndexList>indexes,
        List<String>availableSectors,
        List<String>availableStockTypes,
        int currentPage,
        int totalPages,
        int itemsPerPage,
        int totalCount,
        boolean hasNextPage

) {

    public TickerListData(ListResponse listResponse) {
        this(
                listResponse.stocks(),
                listResponse.indexes(),
                listResponse.availableSectors(),
                listResponse.availableStockTypes(),
                listResponse.currentPage(),
                listResponse.totalPages(),
                listResponse.itemsPerPage(),
                listResponse.totalCount(),
                listResponse.hasNextPage()
        );
    }
}
