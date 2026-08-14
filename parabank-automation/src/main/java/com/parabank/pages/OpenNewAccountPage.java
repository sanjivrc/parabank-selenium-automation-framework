package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By accountTypeDropdown = By.id("type");
    private final By fromAccountDropdown = By.id("fromAccountId");
    private final By openAccountButton = By.cssSelector("input[value='Open New Account']");
    private final By newAccountIdLink = By.id("newAccountId");
    private final By successTitle = By.cssSelector("#openAccountResult h1");

    public OpenNewAccountPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void selectAccountType(String type) {
        new Select(waitUtility.waitForVisibility(accountTypeDropdown)).selectByVisibleText(type);
    }

    public void selectFromAccount(String accountId) {
        new Select(driver.findElement(fromAccountDropdown)).selectByVisibleText(accountId);
    }

    public void clickOpenNewAccount() {
        driver.findElement(openAccountButton).click();
    }

    public String getNewAccountId() {
        return waitUtility.waitForVisibility(newAccountIdLink).getText();
    }

    public String getSuccessTitle() {
        return waitUtility.waitForVisibility(successTitle).getText();
    }
}
