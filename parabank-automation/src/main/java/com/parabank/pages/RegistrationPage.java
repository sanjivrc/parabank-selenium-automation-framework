package com.parabank.pages;

import com.parabank.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the "Register" page (parabank/register.htm).
 * Fields are named in the underlying HTML using dotted names like
 * "customer.firstName" — Selenium's By.name() handles this fine.
 */
public class RegistrationPage {

    private final WebDriver driver;
    private final WaitUtility waitUtility;

    private final By firstName = By.name("customer.firstName");
    private final By lastName = By.name("customer.lastName");
    private final By address = By.name("customer.address.street");
    private final By city = By.name("customer.address.city");
    private final By state = By.name("customer.address.state");
    private final By zipCode = By.name("customer.address.zipCode");
    private final By phoneNumber = By.name("customer.phoneNumber");
    private final By ssn = By.name("customer.ssn");
    private final By username = By.name("customer.username");
    private final By password = By.name("customer.password");
    private final By confirmPassword = By.name("repeatedPassword");
    private final By registerButton = By.cssSelector("input.button[value='Register']");
    private final By successMessage = By.cssSelector("#rightPanel h1");
    private final By errorMessage = By.cssSelector("div.error, span.error");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtility = new WaitUtility(driver);
    }

    public void fillRegistrationForm(String firstNameVal, String lastNameVal, String addressVal, String cityVal,
                                      String stateVal, String zipVal, String phoneVal, String ssnVal,
                                      String usernameVal, String passwordVal) {
        driver.findElement(firstName).sendKeys(firstNameVal);
        driver.findElement(lastName).sendKeys(lastNameVal);
        driver.findElement(address).sendKeys(addressVal);
        driver.findElement(city).sendKeys(cityVal);
        driver.findElement(state).sendKeys(stateVal);
        driver.findElement(zipCode).sendKeys(zipVal);
        driver.findElement(phoneNumber).sendKeys(phoneVal);
        driver.findElement(ssn).sendKeys(ssnVal);
        driver.findElement(username).sendKeys(usernameVal);
        driver.findElement(password).sendKeys(passwordVal);
        driver.findElement(confirmPassword).sendKeys(passwordVal);
    }

    public void clickRegister() {
        waitUtility.waitForClickability(registerButton).click();
    }

    public String getSuccessMessage() {
        return waitUtility.waitForVisibility(successMessage).getText();
    }

    public boolean isErrorDisplayed() {
        try {
            return waitUtility.waitForVisibility(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
