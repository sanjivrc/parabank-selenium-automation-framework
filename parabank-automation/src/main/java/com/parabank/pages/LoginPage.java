package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents the ParaBank login form (present on the home page).
 * Holds locators + actions only — no assertions here, those belong in the test classes.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("input.button[value='Log In']");
    private final By errorMessage = By.cssSelector("div.error, p.error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void enterUsername(String username) {
        WebElement field = waitUtility.waitForVisibility(usernameField);
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement field = waitUtility.waitForVisibility(passwordField);
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        waitUtility.waitForClickability(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return waitUtility.waitForVisibility(errorMessage).getText();
    }

    public boolean isErrorDisplayed() {
        try {
            return waitUtility.waitForVisibility(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
