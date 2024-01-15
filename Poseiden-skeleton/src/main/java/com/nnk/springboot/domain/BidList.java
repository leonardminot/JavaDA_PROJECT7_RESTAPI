package com.nnk.springboot.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue
    private Integer id;
    @Column( name = "bid_list_id")
    private Integer bidListId;
    @Column
    private Double bidQuantity;

    public Integer getId() {
        return id;
    }

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public BidList() {}

    public BidList(String toDetermine1, String toDetermine2, Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }


}
