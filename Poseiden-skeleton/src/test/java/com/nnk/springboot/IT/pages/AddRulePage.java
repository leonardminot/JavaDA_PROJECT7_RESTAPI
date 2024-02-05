package com.nnk.springboot.IT.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddRulePage {
    @FindBy(id = "name")
    private WebElement nameField;
    @FindBy(id = "description")
    private WebElement descriptionField;
    @FindBy(id = "json")
    private WebElement jsonField;
    @FindBy(id = "template")
    private WebElement templateField;
    @FindBy(id = "sql")
    private WebElement sqlField;
    @FindBy(id = "sqlPart")
    private WebElement sqlPartField;
    @FindBy(id = "add-rule-name-btn")
    private WebElement addBtn;

    private final WebDriver webDriver;

    public AddRulePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public RulePage addNewRule(
            String name,
            String description,
            String json,
            String template,
            String sql,
            String sqlPart
    ) {
        nameField.sendKeys(name);
        descriptionField.sendKeys(description);
        jsonField.sendKeys(json);
        templateField.sendKeys(template);
        sqlField.sendKeys(sql);
        sqlPartField.sendKeys(sqlPart);
        addBtn.click();

        return new RulePage(webDriver);
    }
}
