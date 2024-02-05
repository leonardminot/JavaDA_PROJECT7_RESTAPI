package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCurvePointPage {
    @FindBy(id = "curve-id")
    private WebElement curveIdField;
    @FindBy(id = "term")
    private WebElement termField;
    @FindBy(id = "value")
    private WebElement valueField;
    @FindBy(id = "add-curve-point-btn")
    private WebElement addBtn;

    private final WebDriver webDriver;

    public AddCurvePointPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isAddCurvePointPageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addBtn)).isDisplayed();
    }

    public CurvePointPage addNewCurvePoint(String curveId, String term, String value) {
        curveIdField.sendKeys(curveId);
        termField.sendKeys(term);
        valueField.sendKeys(value);
        addBtn.click();

        return new CurvePointPage(webDriver);
    }
}
