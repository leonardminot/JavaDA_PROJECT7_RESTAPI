package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddRatingPage {
    @FindBy(id = "moodysRating")
    private WebElement moodysRatingField;
    @FindBy(id = "sandPRating")
    private WebElement sandPRatingField;
    @FindBy(id = "fitchRating")
    private WebElement fitchRatingField;
    @FindBy(id = "order")
    private WebElement orderField;
    @FindBy(id = "add-rating-btn")
    private WebElement addBtnField;
    private final WebDriver webDriver;

    public AddRatingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public RatingPage addNewRating(
            String moodysRating,
            String sandPRating,
            String fitchRating,
            String orderNumber
    ) {
        moodysRatingField.sendKeys(moodysRating);
        sandPRatingField.sendKeys(sandPRating);
        fitchRatingField.sendKeys(fitchRating);
        orderField.sendKeys(orderNumber);
        addBtnField.click();

        return new RatingPage(webDriver);
    }
}
