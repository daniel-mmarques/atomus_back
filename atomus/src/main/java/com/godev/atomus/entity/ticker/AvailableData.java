package com.godev.atomus.entity.ticker;

import com.godev.atomus.client.brapi.response.AvailableResponse;

import java.util.List;

public record AvailableData(

        List<String> stocks

) {

    public AvailableData(AvailableResponse availableResponse) {
        this(
                availableResponse.stocks()
        );
    }
}
