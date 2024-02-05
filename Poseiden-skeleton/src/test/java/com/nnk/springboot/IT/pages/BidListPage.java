package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BidListPage {
    @FindBy(id = "add-new-bid")
    private WebElement addBidListBtn;

    private final WebDriver webDriver;

    public BidListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isBidListPageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addBidListBtn)).isDisplayed();
    }

    public AddBidListPage addNewBidList() {
        addBidListBtn.click();
        return new AddBidListPage(webDriver);
    }
}
