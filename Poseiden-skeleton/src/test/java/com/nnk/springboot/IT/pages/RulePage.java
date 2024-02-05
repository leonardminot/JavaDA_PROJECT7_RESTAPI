package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RulePage {
    @FindBy(id = "add-new-rule")
    private WebElement addRuleBtn;
    private final WebDriver webDriver;

    public RulePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isRulePageDisplayed() {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        return waiter.until(ExpectedConditions.visibilityOf(addRuleBtn)).isDisplayed();
    }

    public AddRulePage addNewRule() {
        addRuleBtn.click();
        return new AddRulePage(webDriver);
    }
}
