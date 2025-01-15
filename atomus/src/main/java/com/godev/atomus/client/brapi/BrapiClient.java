package com.godev.atomus.client.brapi;

import com.godev.atomus.client.brapi.response.AvailableResponse;
import com.godev.atomus.client.brapi.response.DetailsResponse;
import com.godev.atomus.client.brapi.response.ListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "client", url = "https://brapi.dev/api/")
public interface BrapiClient {

    @GetMapping(value = "quote/{ticker}")
    DetailsResponse getTicker(@PathVariable String ticker, @RequestHeader("Authorization") String authorization);

    @GetMapping(value = "quote/list")
    ListResponse getList(@RequestHeader("Authorization") String authorization);

    @GetMapping(value = "quote/list?type={type}")
    ListResponse getListByType(@PathVariable String type, @RequestHeader("Authorization") String authorization);

    @GetMapping(value = "available?search={ticker}")
    AvailableResponse getAvailable(@PathVariable String ticker, @RequestHeader("Authorization") String authorization);
}
