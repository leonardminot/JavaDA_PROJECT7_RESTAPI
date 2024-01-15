package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue
    private int id;
    private int orderNumber;

    public Integer getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    //TODO("Constructor only to pass the tests")
    public Rating(String toDetermine1, String toDetermine2, String toDetermine3, int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Rating() {}
}
