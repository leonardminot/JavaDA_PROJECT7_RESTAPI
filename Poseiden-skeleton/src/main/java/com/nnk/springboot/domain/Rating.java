package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "Moodys Rating must not be blank.")
    private String moodysRating;
    @NotBlank(message = "Sand P. must not be blank.")
    private String sandPRating;
    @NotBlank(message = "Fitch Rating must not be blank.")
    private String fitchRating;
    @NotNull
    private Integer orderNumber;

    public Integer getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.orderNumber = orderNumber;
        this.moodysRating = moodysRating;
        this.fitchRating = fitchRating;
        this.sandPRating = sandPRating;
    }

    public Rating() {}
}
