package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue
    private Integer bidListId;
    @NotBlank(message = "Account must not be empty")
    private String account;
    @NotBlank(message = "Type must not be empty")
    private String type;
    @NotNull(message = "Bid Quantity must not be null")
    private Double bidQuantity;
    @NotNull(message = "Ask quantity must not be null")
    private Double askQuantity;
    @NotNull(message = "Bid must not be null")
    private Double bid;
    @NotNull(message = "Ask must not be null")
    private Double ask;
    @NotBlank(message = "Benchmark must not be empty")
    private String benchmark;
    private Timestamp bidListDate;

    public BidList(String account, String type, Double bidQuantity, Double askQuantity, Double bid, Double ask, String benchmark, Timestamp bidListDate) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = bidListDate;
    }

    public BidList(String account, String type, Double bidQuantity, Double askQuantity, Double bid, Double ask, String benchmark) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
    }

    public BidList() {}

    public BidList(String toDetermine1, String toDetermine2, Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }
}
