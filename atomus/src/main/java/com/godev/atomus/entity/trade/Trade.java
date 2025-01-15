package com.godev.atomus.entity.trade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Table(name = "trade")
@Entity(name = "Trade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private EnumTradeType tradeType;
    private LocalDate tradeDate;
    private Long brokerId;
    private Long productId;
    private String title;
    private Integer quantity;
    private Double unitPrice;
    private Double fees;

    public Trade(TradeRegisterData tradeRegisterData) {
        this.userId = tradeRegisterData.userId();
        this.tradeType = tradeRegisterData.tradeType();
        this.tradeDate = tradeRegisterData.tradeDate();
        this.brokerId = tradeRegisterData.brokerId();
        this.productId = tradeRegisterData.productId();
        this.title = tradeRegisterData.title();
        this.quantity = tradeRegisterData.quantity();
        this.unitPrice = tradeRegisterData.unitPrice();
        this.fees = tradeRegisterData.fees();
    }

    public void editTrade(TradeEditData tradeEditData) {
        this.tradeDate = tradeEditData.tradeDate();
        this.brokerId = tradeEditData.brokerId();
        this.quantity = tradeEditData.quantity();
        this.unitPrice = tradeEditData.unitPrice();
        this.fees = tradeEditData.fees();
    }
}
