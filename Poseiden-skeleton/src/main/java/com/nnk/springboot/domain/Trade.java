package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue
    private Integer id;

    private Integer tradeId;
    private String account;

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public String getAccount() {
        return account;
    }

    public Trade(String account, String toDetermine) {
        this.account = account;
    }

    public Trade() {}
}
