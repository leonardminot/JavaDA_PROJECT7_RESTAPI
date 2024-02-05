package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CurvePointPage {
    @FindBy(id = "add-new-curve-point")
    private WebElement addCurvePointBtn;

    private final WebDriver webDriver;

    public CurvePointPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isBidListPageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addCurvePointBtn)).isDisplayed();
    }

    public AddCurvePointPage addNewCurvePoint() {
        addCurvePointBtn.click();
        return new AddCurvePointPage(webDriver);
    }
}
