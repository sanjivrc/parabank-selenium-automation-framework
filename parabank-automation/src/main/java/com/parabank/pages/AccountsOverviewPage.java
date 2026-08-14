package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountsOverviewPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By pageTitle = By.cssSelector("#accountTable, h1.title");
    private final By accountRows = By.cssSelector("#accountTable tbody tr");
    private final By accountLinks = By.cssSelector("#accountTable tbody tr td a");

    public AccountsOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public boolean isOverviewPageDisplayed() {
        try {
            waitUtility.waitForVisibility(pageTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getAccountCount() {
        List<WebElement> rows = driver.findElements(accountRows);
        return rows.size();
    }

    public List<WebElement> getAccountLinks() {
        return driver.findElements(accountLinks);
    }

    public String getFirstAccountId() {
        List<WebElement> links = getAccountLinks();
        return links.isEmpty() ? null : links.get(0).getText();
    }
}
