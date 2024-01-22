package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue
    private Integer tradeId;
    @NotBlank(message = "Account must not be empty")
    private String account;
    @NotBlank(message = "Type must not be empty")
    private String type;
    @NotNull(message = "Buy Quantity must not be null")
    private Double buyQuantity;
    @NotNull(message = "Sell quantity must not be null")
    private Double sellQuantity;
    @NotNull(message = "Buy price must not be null")
    private Double buyPrice;
    @NotNull(message = "Sell price must not be null")
    private Double sellPrice;
    @NotBlank(message = "Benchmark must not be empty")
    private String benchmark;
    private Timestamp tradeDate;


    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getAccount() {
        return account;
    }

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    public Trade(String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, String benchmark) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.benchmark = benchmark;
    }

    public Trade(String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, String benchmark, Timestamp tradeDate) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.benchmark = benchmark;
        this.tradeDate = tradeDate;
    }

    public Trade() {}
}
