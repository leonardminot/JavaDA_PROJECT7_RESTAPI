package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    @FindBy(id = "home-page-card")
    private WebElement homePageCard;
    @FindBy(id = "bid-list-link")
    private WebElement bidListLink;
    @FindBy(id = "trade-link")
    private WebElement tradeLink;
    @FindBy(id = "curve-point-link")
    private WebElement curvePointLink;
    @FindBy(id = "rating-link")
    private WebElement ratingLink;
    @FindBy(id = "rule-name-link")
    private WebElement ruleLink;

    private final WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHomePageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return waiter.until(ExpectedConditions.visibilityOf(homePageCard)).isDisplayed();
    }

    public BidListPage navigateToBidList() {
        bidListLink.click();
        return new BidListPage(webDriver);
    }

    public TradePage navigateToTrade() {
        tradeLink.click();
        return new TradePage(webDriver);
    }

    public CurvePointPage navigateToCurvePoint() {
        curvePointLink.click();
        return new CurvePointPage(webDriver);
    }

    public RatingPage navigateToRatingPage() {
        ratingLink.click();
        return new RatingPage(webDriver);
    }

    public RulePage navigateToRulePage() {
        ruleLink.click();
        return new RulePage(webDriver);
    }


}
