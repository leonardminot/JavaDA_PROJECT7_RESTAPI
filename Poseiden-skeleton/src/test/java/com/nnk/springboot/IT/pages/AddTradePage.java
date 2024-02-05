package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddTradePage {
    @FindBy(id = "account")
    private WebElement accountField;
    @FindBy(id = "type")
    private WebElement typeField;
    @FindBy(id = "buyQuantity")
    private WebElement buyQuantityField;
    @FindBy(id = "sellQuantity")
    private WebElement sellQuantityField;
    @FindBy(id = "buyPrice")
    private WebElement buyPriceField;
    @FindBy(id = "sellPrice")
    private WebElement sellPriceField;
    @FindBy(id = "benchmark")
    private WebElement benchmarkField;
    @FindBy(id = "commentary")
    private WebElement commentaryField;
    @FindBy(id = "security")
    private WebElement securityField;
    @FindBy(id = "status")
    private WebElement statusField;
    @FindBy(id = "trader")
    private WebElement traderField;
    @FindBy(id = "book")
    private WebElement bookField;
    @FindBy(id = "creationName")
    private WebElement creationNameField;
    @FindBy(id = "revisionName")
    private WebElement revisionNameField;
    @FindBy(id = "dealName")
    private WebElement dealNameField;
    @FindBy(id = "dealType")
    private WebElement dealTypeField;
    @FindBy(id = "sourceListId")
    private WebElement sourceListIdField;
    @FindBy(id = "side")
    private WebElement sideField;
    @FindBy(id = "add-trade-btn")
    private WebElement addBtn;
    private final WebDriver webDriver;

    public AddTradePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isAddTradePageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addBtn)).isDisplayed();
    }

    public TradePage addNewTrade(
            String account,
            String type,
            String buyQuantity,
            String sellQuantity,
            String buyPrice,
            String sellPrice,
            String benchmark,
            String commentary,
            String security,
            String status,
            String trader,
            String book,
            String creationName,
            String revisionName,
            String dealName,
            String dealType,
            String sourceListId,
            String side
    ) {
        accountField.sendKeys(account);
        typeField.sendKeys(type);
        buyQuantityField.sendKeys(buyQuantity);
        sellQuantityField.sendKeys(sellQuantity);
        buyPriceField.sendKeys(buyPrice);
        sellPriceField.sendKeys(sellPrice);
        benchmarkField.sendKeys(benchmark);
        commentaryField.sendKeys(commentary);
        securityField.sendKeys(security);
        statusField.sendKeys(status);
        traderField.sendKeys(trader);
        bookField.sendKeys(book);
        creationNameField.sendKeys(creationName);
        revisionNameField.sendKeys(revisionName);
        dealNameField.sendKeys(dealName);
        dealTypeField.sendKeys(dealType);
        sourceListIdField.sendKeys(sourceListId);
        sideField.sendKeys(side);
        addBtn.click();

        return new TradePage(webDriver);
    }
}
