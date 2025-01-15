package com.godev.atomus.service;

import com.godev.atomus.client.brapi.response.DetailsResponse;
import com.godev.atomus.client.brapi.ticker.TickerDetails;
import com.godev.atomus.entity.portfolio.PortfolioProductData;
import com.godev.atomus.entity.portfolio.PortfolioTickerData;
import com.godev.atomus.entity.portfolio.PortfolioData;
import com.godev.atomus.entity.ticker.TickerDetailsData;
import com.godev.atomus.entity.trade.EnumTradeType;
import com.godev.atomus.entity.trade.Trade;
import com.godev.atomus.entity.trade.TradeRegisterData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PortfolioServiceTest {

    @Mock
    private TradeService tradeService;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private PortfolioService portfolioService;

    @Test
    @DisplayName("Should return portfolio data for a user")
    void testGetPortfolio() {

        when(tradeService.findProductsByUsuarioId(any(Long.class)))
                .thenReturn(Arrays.asList("stock", "fund"));

        when(tradeService.findAllByUsuarioIdAndProduct(any(Long.class), any(String.class)))
                .thenReturn(Arrays.asList(
                    new Trade(new TradeRegisterData(
                            1L,
                            EnumTradeType.COMPRA,
                            LocalDate.now(),
                            1L,
                            1L,
                            "B3SA3",
                            5,
                            10.0,
                            0.0
                    )),
                    new Trade(new TradeRegisterData(
                            1L,
                            EnumTradeType.VENDA,
                            LocalDate.now(),
                            1L,
                            1L,
                            "B3SA3",
                            2,
                            10.0,
                            0.0
                    ))
            )
        );

        when(searchService.searchDetails(any(String.class)))
                .thenReturn(new TickerDetailsData(new DetailsResponse(
                    Collections.singletonList(new TickerDetails(
                            "B3SA3",
                            "B3 S.A. - Brasil, Bolsa, Balcão",
                            64572993536L,
                            10,
                            0.010000229,
                            0.086357765,
                            12.80875,
                            -1.21875,
                            -0.0951498,
                            11.65,
                            11.47,
                            14899700,
                            0.7215913,
                            "https://s3-symbol-logo.tradingview.com/b3-on-nm--big.svg",
                            "2024-05-08T17:11:24.000Z"
                    )),
                "2024-05-08T20:07:32.000Z",
                "746ms"
        )));

        PortfolioData portfolioData = portfolioService.getPortfolio(123L);
        assertNotNull(portfolioData);
    }

    @Test
    @DisplayName("Should return portfolio data for a specific product of a user")
    void testGetPortfolioByProduct() {

        when(tradeService.findAllByUsuarioIdAndProduct(any(Long.class), any(String.class))).
                thenReturn(Arrays.asList(
                new Trade(new TradeRegisterData(
                        1L,
                        EnumTradeType.COMPRA,
                        LocalDate.now(),
                        1L,
                        1L,
                        "B3SA3",
                        5,
                        10.0,
                        0.0
                )),
                new Trade(new TradeRegisterData(
                        1L,
                        EnumTradeType.VENDA,
                        LocalDate.now(),
                        1L,
                        1L,
                        "B3SA3",
                        2,
                        10.0,
                        0.0
                ))
        ));

        when(searchService.searchDetails(any(String.class)))
                .thenReturn(new TickerDetailsData(new DetailsResponse(
                        Collections.singletonList(new TickerDetails(
                                "B3SA3",
                                "B3 S.A. - Brasil, Bolsa, Balcão",
                                64572993536L,
                                10,
                                0.010000229,
                                0.086357765,
                                12.80875,
                                -1.21875,
                                -0.0951498,
                                11.65,
                                11.47,
                                14899700,
                                0.7215913,
                                "https://s3-symbol-logo.tradingview.com/b3-on-nm--big.svg",
                                "2024-05-08T17:11:24.000Z"
                        )),
                        "2024-05-08T20:07:32.000Z",
                        "746ms"
                )));

        PortfolioProductData portfolioByProductData = portfolioService.getPortfolioByProduct(1L, "stock");
        assertNotNull(portfolioByProductData);
    }

    @Test
    @DisplayName("Should return portfolio data for a specific title of a product of a user")
    void testGetPortfolioByTitle() {

        List<Trade> trades = new ArrayList<>();
        when(tradeService.findAllByUsuarioIdAndTitle(any(Long.class), any(String.class), any(String.class)))
                .thenReturn(Arrays.asList(
                        new Trade(new TradeRegisterData(
                                1L,
                                EnumTradeType.COMPRA,
                                LocalDate.now(),
                                1L,
                                1L,
                                "B3SA3",
                                5,
                                10.0,
                                0.0
                        )),
                        new Trade(new TradeRegisterData(
                                1L,
                                EnumTradeType.VENDA,
                                LocalDate.now(),
                                1L,
                                1L,
                                "B3SA3",
                                2,
                                10.0,
                                0.0
                        ))
                ));

        when(searchService.searchDetails("B3SA3")).thenReturn(new TickerDetailsData(new DetailsResponse(
                Collections.singletonList(
                        new TickerDetails(
                                "B3SA3",
                                "B3 S.A. - Brasil, Bolsa, Balcão",
                                64572993536L,
                                10,
                                0.010000229,
                                0.086357765,
                                12.80875,
                                -1.21875,
                                -0.0951498,
                                11.65,
                                11.47,
                                14899700,
                                0.7215913,
                                "https://s3-symbol-logo.tradingview.com/b3-on-nm--big.svg",
                                "2024-05-08T17:11:24.000Z"
                        )
                ),
                "2024-05-08T20:07:32.000Z",
                "746ms"
        )));

        PortfolioTickerData portfolioByTitleData = portfolioService.getPortfolioByTitle(1L, "stock", "B3SA3");
        assertNotNull(portfolioByTitleData);
    }

    @Test
    @DisplayName("Should calculate profitability correctly")
    public void testGetProfitability() {

        BigDecimal averagePrice = BigDecimal.TEN;
        BigDecimal regularMarketPrice = BigDecimal.valueOf(15);

        BigDecimal profitability = portfolioService.getProfitability(averagePrice, regularMarketPrice);
        assertEquals(BigDecimal.valueOf(50).setScale(3), profitability);
    }

    @Test
    @DisplayName("Should calculate average price correctly")
    public void testGetAveragePrice() {

        Map<String, BigDecimal[]> mappedList = new HashMap<>();
        BigDecimal[] array = {BigDecimal.valueOf(3), BigDecimal.valueOf(45)};

        mappedList.put("B3SA3", array);
        array = new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(20)};
        mappedList.put("B3SA3", array);
        array = new BigDecimal[]{BigDecimal.valueOf(5), BigDecimal.valueOf(100)};
        mappedList.put("B3SA3", array);

        BigDecimal averagePrice = portfolioService.getAveragePrice(mappedList);
        assertEquals(BigDecimal.valueOf(20), averagePrice);
    }

    @Test
    @DisplayName("Should calculate total amount correctly from list")
    public void testGetTotalAmountByMapWithList() {

        List<PortfolioTickerData> portfolioTickerDataListStock = new ArrayList<>();
        List<PortfolioTickerData> portfolioTickerDataListFund = new ArrayList<>();
        List<PortfolioProductData> portfolioProductDataList = new ArrayList<>();

        PortfolioTickerData portfolioTickerData1Stock = new PortfolioTickerData(
                "B3SA3",
                BigDecimal.valueOf(100),
                10,
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(0)
        );

        portfolioTickerDataListStock.add(portfolioTickerData1Stock);

        PortfolioTickerData portfolioTickerData2Stock = new PortfolioTickerData(
                "VALE3",
                BigDecimal.valueOf(150),
                3,
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(0)
        );

        portfolioTickerDataListStock.add(portfolioTickerData2Stock);
        portfolioProductDataList.add(new PortfolioProductData("stock", BigDecimal.valueOf(250), portfolioTickerDataListStock));

        PortfolioTickerData portfolioTickerData1Fund = new PortfolioTickerData(
                "MXRF11",
                BigDecimal.valueOf(100),
                10,
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(0)
        );

        portfolioTickerDataListStock.add(portfolioTickerData1Fund);
        portfolioProductDataList.add(new PortfolioProductData("fund", BigDecimal.valueOf(150), portfolioTickerDataListFund));

        BigDecimal totalAmount = portfolioService.getTotalAmountByList(portfolioProductDataList);
        assertEquals(BigDecimal.valueOf(400), totalAmount);
    }

    @Test
    @DisplayName("Should calculate total amount correctly from map")
    public void testGetTotalAmountByMap() {

        Map<String, BigDecimal[]> map = new HashMap<>();
        BigDecimal[] array = {BigDecimal.valueOf(3), BigDecimal.valueOf(30)};
        map.put("B3SA3", array);
        array = new BigDecimal[]{BigDecimal.valueOf(2), BigDecimal.valueOf(100)};
        map.put("VALE3", array);

        when(searchService.searchDetails("B3SA3"))
                .thenReturn(new TickerDetailsData(new DetailsResponse(
                        Collections.singletonList(new TickerDetails(
                                "B3SA3",
                                "B3 S.A. - Brasil, Bolsa, Balcão",
                                64572993536L,
                                10,
                                0.010000229,
                                0.086357765,
                                12.80875,
                                -1.21875,
                                -0.0951498,
                                11.65,
                                11.47,
                                14899700,
                                0.7215913,
                                "https://s3-symbol-logo.tradingview.com/b3-on-nm--big.svg",
                                "2024-05-08T17:11:24.000Z"
                        )),
                        "2024-05-08T20:07:32.000Z",
                        "746ms"
                )));

        when(searchService.searchDetails("VALE3"))
                .thenReturn(new TickerDetailsData(new DetailsResponse(
                        Collections.singletonList(new TickerDetails(
                                "VALE3",
                                "Vale S.A.",
                                273898323968L,
                                60,
                                -0.59000015,
                                -0.9135958,
                                67.56755,
                                -3.577549,
                                -0.052947737,
                                64.23,
                                63.59,
                                18159700,
                                8.9744931,
                                "https://s3-symbol-logo.tradingview.com/vale--big.svg",
                                "2024-05-08T20:07:32.000Z"
                        )),
                        "2024-03-12T10:00:00.000Z",
                        "746ms"
                )));

        BigDecimal totalAmount = portfolioService.getTotalAmountByMap(map);
        assertEquals(BigDecimal.valueOf(150.0), totalAmount);
    }

    @Test
    @DisplayName("Should analyze trades correctly")
    void analyzeTrades() {
        List<Trade> trades = Arrays.asList(
                new Trade(new TradeRegisterData(1L, EnumTradeType.COMPRA, LocalDate.now(), 1L, 1L, "B3SA3", 5, 11.0, 0.0)),
                new Trade(new TradeRegisterData(1L, EnumTradeType.VENDA, LocalDate.now(), 1L, 1L, "B3SA3", 2, 11.0, 0.0)),
                new Trade(new TradeRegisterData(1L, EnumTradeType.COMPRA, LocalDate.now(), 1L, 1L, "VALE3", 2, 50.0, 0.0))
        );

        Map<String, BigDecimal[]> mappedListByAnalyzeTrades = portfolioService.analyzeTrades(trades);

        assertEquals(2, mappedListByAnalyzeTrades.size());

        BigDecimal[] expectedB3SA3 = {BigDecimal.valueOf(3), BigDecimal.valueOf(33.0)};
        BigDecimal[] expectedVALE3 = {BigDecimal.valueOf(2), BigDecimal.valueOf(100.0)};

        assertArrayEquals(expectedB3SA3, mappedListByAnalyzeTrades.get("B3SA3"));
        assertArrayEquals(expectedVALE3, mappedListByAnalyzeTrades.get("VALE3"));
    }
}