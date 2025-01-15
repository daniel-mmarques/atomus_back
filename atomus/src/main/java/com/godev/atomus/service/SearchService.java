package com.godev.atomus.service;

import com.godev.atomus.client.brapi.BrapiClient;
import com.godev.atomus.entity.ticker.AvailableData;
import com.godev.atomus.entity.ticker.TickerDetailsData;
import com.godev.atomus.entity.ticker.TickerListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private BrapiClient brapiClient;

    @Autowired
    @Value("${brapi.api.token}")
    private String apiToken;

    public TickerDetailsData searchDetails(String ticker) { return new TickerDetailsData(brapiClient.getTicker(ticker, apiToken)); }

    public TickerListData searchList() { return new TickerListData(brapiClient.getList(apiToken)); }

    public TickerListData searchListByType(String type) { return new TickerListData(brapiClient.getListByType(type, apiToken)); }

    public AvailableData searchAvailable(String ticker) { return new AvailableData(brapiClient.getAvailable(ticker, apiToken)); }
}
