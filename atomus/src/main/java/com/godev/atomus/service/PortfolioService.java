package com.godev.atomus.service;

import com.godev.atomus.entity.portfolio.PortfolioProductData;
import com.godev.atomus.entity.portfolio.PortfolioTickerData;
import com.godev.atomus.entity.portfolio.PortfolioData;
import com.godev.atomus.entity.trade.EnumTradeType;
import com.godev.atomus.entity.trade.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    @Autowired
    private SearchService searchService;

    @Autowired
    private TradeService tradeService;

    public PortfolioData getPortfolio(Long userId) {

        List<String> products = tradeService.findProductsByUsuarioId(userId);
        List<PortfolioProductData> portfolioByProductDataList = new ArrayList<>();

        for (String product : products) {
            Map<String, BigDecimal[]> mappedTrades = analyzeTrades(tradeService.findAllByUsuarioIdAndProduct(userId, product));
            List<PortfolioTickerData> portfolioByTitleDataList = new ArrayList<>();

            for (Map.Entry<String, BigDecimal[]> entry : mappedTrades.entrySet()) {
                String title = entry.getKey();
                BigDecimal[] tradeValues = entry.getValue();
                BigDecimal regularMarketPrice = BigDecimal.valueOf(searchService.searchDetails(title).regularMarketPrice());
                BigDecimal averagePrice = tradeValues[1].divide(tradeValues[0], RoundingMode.HALF_EVEN);
                BigDecimal totalAmount = tradeValues[1];
                int quantity = tradeValues[0].intValue();
                BigDecimal profitability = getProfitability(averagePrice, regularMarketPrice);

                portfolioByTitleDataList.add(new PortfolioTickerData(title, totalAmount, quantity, averagePrice, regularMarketPrice, profitability));
            }

            BigDecimal totalAmountForProduct = getTotalAmountByMap(mappedTrades);
            portfolioByProductDataList.add(new PortfolioProductData(product, totalAmountForProduct, portfolioByTitleDataList));
        }

        BigDecimal totalAmount = getTotalAmountByList(portfolioByProductDataList);

        return new PortfolioData(userId, totalAmount, portfolioByProductDataList);
    }

    public PortfolioProductData getPortfolioByProduct(Long userId, String product) {

        Map<String, BigDecimal[]> mappedTrades = analyzeTrades(tradeService.findAllByUsuarioIdAndProduct(userId, product));
        List<PortfolioTickerData> portfolioTickerDataList = new ArrayList<>();

        for (Map.Entry<String, BigDecimal[]> entry : mappedTrades.entrySet()) {

            String title = entry.getKey();
            BigDecimal[] tradeValues = entry.getValue();
            BigDecimal regularMarketPrice = BigDecimal.valueOf(searchService.searchDetails(title).regularMarketPrice());
            BigDecimal averagePrice = entry.getValue()[1].divide(entry.getValue()[0], RoundingMode.HALF_EVEN);
            BigDecimal totalAmount = entry.getValue()[1];
            int quantity = tradeValues[0].intValue();
            BigDecimal profitability = getProfitability(averagePrice, regularMarketPrice);

            portfolioTickerDataList.add(new PortfolioTickerData(title, totalAmount, quantity, averagePrice, regularMarketPrice, profitability));
        }

        return new PortfolioProductData(product, getTotalAmountByMap(mappedTrades), portfolioTickerDataList);
    }

    public PortfolioTickerData getPortfolioByTitle(Long userId, String product, String title) {

        List<Trade> trades = tradeService.findAllByUsuarioIdAndTitle(userId, product, title);
        Map<String, BigDecimal[]> mappedTrades = analyzeTrades(trades);
        BigDecimal regularMarketPrice = BigDecimal.valueOf(searchService.searchDetails(title).regularMarketPrice());
        BigDecimal averagePrice = getAveragePrice(mappedTrades);
        int quantity = mappedTrades.get(title)[0].intValue();
        BigDecimal totalAmount = getTotalAmountByMap(mappedTrades);
        BigDecimal profitability = getProfitability(averagePrice, regularMarketPrice);

        return new PortfolioTickerData(title, totalAmount, quantity, averagePrice, regularMarketPrice, profitability);
    }

    public BigDecimal getProfitability(BigDecimal averagePrice, BigDecimal regularMarketPrice) {

        BigDecimal difference = regularMarketPrice.subtract(averagePrice);

        return difference.divide(averagePrice, 3, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(100));
    }

    public BigDecimal getAveragePrice(Map<String, BigDecimal[]> mappedList) {

        BigDecimal sumOfTotalPrice = BigDecimal.ZERO;
        int totalQuantity = 0;

        for (Map.Entry<String, BigDecimal[]> entry : mappedList.entrySet()) {
            BigDecimal totalPrice = entry.getValue()[1];
            int quantity = entry.getValue()[0].intValue();
            sumOfTotalPrice = sumOfTotalPrice.add(totalPrice);
            totalQuantity += quantity;
        }

        return totalQuantity != 0 ? sumOfTotalPrice.divide(BigDecimal.valueOf(totalQuantity), RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    public BigDecimal getTotalAmountByList(List<PortfolioProductData> portfolioByProductDataList) {

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (PortfolioProductData productData : portfolioByProductDataList) {
            totalAmount = totalAmount.add(productData.totalAmount());
        }

        return totalAmount;
    }

    public BigDecimal getTotalAmountByMap(Map<String, BigDecimal[]> mappedList) {

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Map.Entry<String, BigDecimal[]> entry : mappedList.entrySet()) {
            String title = entry.getKey();
            BigDecimal quantity = entry.getValue()[0];
            BigDecimal regularMarketPrice = BigDecimal.valueOf(searchService.searchDetails(title).regularMarketPrice());
            BigDecimal totalAmountTitle = regularMarketPrice.multiply(quantity);
            totalAmount = totalAmount.add(totalAmountTitle);
        }

        return totalAmount;
    }

    public Map<String, BigDecimal[]> analyzeTrades(List<Trade> trades) {

        Map<String, BigDecimal[]> result = new HashMap<>();
        Map<String, Integer> totalQuantities = new HashMap<>();
        Map<String, BigDecimal> totalInvestments = new HashMap<>();

        for (Trade trade : trades) {

            String ticker = trade.getTitle();
            int quantity = trade.getQuantity();
            double unitPrice = trade.getUnitPrice();
            BigDecimal investment = BigDecimal.valueOf(quantity).multiply(BigDecimal.valueOf(unitPrice));

            if (trade.getTradeType() == EnumTradeType.COMPRA) {
                totalQuantities.put(ticker, totalQuantities.getOrDefault(ticker, 0) + quantity);
                totalInvestments.put(ticker, totalInvestments.getOrDefault(ticker, BigDecimal.ZERO).add(investment));
            } else if (trade.getTradeType() == EnumTradeType.VENDA) {
                totalQuantities.put(ticker, totalQuantities.getOrDefault(ticker, 0) - quantity);
                totalInvestments.put(ticker, totalInvestments.getOrDefault(ticker, BigDecimal.ZERO).subtract(investment));
            }
        }

        for (String ticker : totalQuantities.keySet()) {
            int totalQuantity = totalQuantities.get(ticker);
            BigDecimal totalInvestment = totalInvestments.get(ticker);
            if (totalQuantity != 0) {
                BigDecimal[] values = {BigDecimal.valueOf(totalQuantity), totalInvestment};
                result.put(ticker, values);
            }
        }

        return result;
    }
}