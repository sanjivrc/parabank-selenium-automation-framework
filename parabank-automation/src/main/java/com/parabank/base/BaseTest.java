package com.parabank.base;

import com.parabank.driver.DriverFactory;
import com.parabank.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Every test class extends this. Centralizes what would otherwise be
 * duplicated setup/teardown code in every single test class:
 * - launching the correct browser (via DriverFactory)
 * - navigating to the base URL
 * - quitting the browser after each test
 *
 * Because DriverFactory uses a ThreadLocal, this class is already safe
 * for TestNG's parallel="methods"/"classes" execution.
 */
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        logger.info("Launching browser: " + browser);

        WebDriver driver = DriverFactory.initDriver(browser);
        driver.get(ConfigReader.getBaseUrl());
        logger.info("Navigated to base URL: " + ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing browser session");
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
