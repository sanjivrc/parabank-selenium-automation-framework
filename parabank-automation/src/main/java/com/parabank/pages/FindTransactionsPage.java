package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class FindTransactionsPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By accountDropdown = By.id("accountId");
    private final By transactionIdField = By.id("transactionId");
    private final By findByIdButton = By.cssSelector("#transactionId ~ button, #findById button");
    private final By resultsTable = By.id("transactionResultsTable");

    public FindTransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void selectAccount(String accountId) {
        new Select(waitUtility.waitForVisibility(accountDropdown)).selectByVisibleText(accountId);
    }

    public void searchByTransactionId(String transactionId) {
        driver.findElement(transactionIdField).sendKeys(transactionId);
        driver.findElement(By.id("findById")).click();
    }

    public boolean isResultsTableDisplayed() {
        try {
            return waitUtility.waitForVisibility(resultsTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
