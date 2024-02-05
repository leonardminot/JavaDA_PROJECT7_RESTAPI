package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddBidListPage {
    @FindBy(id = "account")
    private WebElement accountField;
    @FindBy(id = "type")
    private WebElement typeField;
    @FindBy(id = "bidQuantity")
    private WebElement bidQuantityField;
    @FindBy(id = "askQuantity")
    private WebElement askQuantityField;
    @FindBy(id = "bid")
    private WebElement bidField;
    @FindBy(id = "ask")
    private WebElement askField;
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
    @FindBy(id = "add-bid-list-btn")
    private WebElement addBtn;

    private final WebDriver webDriver;

    public AddBidListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isAddBidListPageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addBtn)).isDisplayed();
    }

    public BidListPage addNewBidList(
            String account,
            String type,
            String bidQuantity,
            String askQuantity,
            String bid,
            String ask,
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
        bidQuantityField.sendKeys(bidQuantity);
        askQuantityField.sendKeys(askQuantity);
        bidField.sendKeys(bid);
        askField.sendKeys(ask);
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

        return new BidListPage(webDriver);
    }
}
