package com.parabank.tests;

import com.parabank.base.BaseTest;
import com.parabank.pages.RegistrationPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void testValidRegistration() {
        getDriver().findElement(By.linkText("Register")).click();

        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        long unique = System.currentTimeMillis();

        registrationPage.fillRegistrationForm(
                "John", "Doe", "123 Main St", "Springfield", "IL", "62704",
                "5555551234", "123456789",
                "johndoe" + unique, "Passw0rd!"
        );
        registrationPage.clickRegister();

        Assert.assertTrue(registrationPage.getSuccessMessage().contains("Welcome"),
                "Expected a welcome message after successful registration");
    }

    @DataProvider(name = "incompleteRegistrationData")
    public Object[][] incompleteRegistrationData() {
        return new Object[][]{
                {"", "Doe"},      // missing first name
                {"John", ""}      // missing last name
        };
    }

    @Test(priority = 2, groups = {"regression"}, dataProvider = "incompleteRegistrationData")
    public void testRegistrationWithMissingFields(String firstName, String lastName) {
        getDriver().findElement(By.linkText("Register")).click();

        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        long unique = System.currentTimeMillis();

        registrationPage.fillRegistrationForm(
                firstName, lastName, "123 Main St", "Springfield", "IL", "62704",
                "5555551234", "123456789",
                "user" + unique, "Passw0rd!"
        );
        registrationPage.clickRegister();

        Assert.assertTrue(registrationPage.isErrorDisplayed(),
                "Expected a validation error when a required field is missing");
    }
}
