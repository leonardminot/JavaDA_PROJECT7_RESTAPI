package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RatingPage {
    @FindBy(id = "add-new-rating")
    private WebElement addRatingBtn;
    private final WebDriver webDriver;

    public RatingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public boolean isRatingPageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return waiter.until(ExpectedConditions.visibilityOf(addRatingBtn)).isDisplayed();
    }

    public AddRatingPage addNewRating() {
        addRatingBtn.click();
        return new AddRatingPage(webDriver);
    }

}
