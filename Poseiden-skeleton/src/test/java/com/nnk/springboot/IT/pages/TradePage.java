package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TradePage {
    @FindBy(id = "add-new-trade")
    private WebElement addTradeBtn;

    private final WebDriver webDriver;

    public TradePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isTradePageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addTradeBtn)).isDisplayed();
    }

    public AddTradePage addNewTrade() {
        addTradeBtn.click();
        return new AddTradePage(webDriver);
    }
}
