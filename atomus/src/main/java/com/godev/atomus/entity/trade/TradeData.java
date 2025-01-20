package com.godev.atomus.entity.trade;

import java.time.LocalDate;

public record TradeData(Long id, Long userId, EnumTradeType tradeType, LocalDate tradeDate, Long brokerId, Long productId, String title, Integer quantity, Double unitPrice, Double fees) {
    public TradeData(Trade trade) {
        this(trade.getId(), trade.getUserId(), trade.getTradeType(), trade.getTradeDate(), trade.getBrokerId(), trade.getProductId(), trade.getTitle(), trade.getQuantity(), trade.getUnitPrice(), trade.getFees());
    }
}
