package com.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/login");
    }

    @Test
    public void testValidLogin() {
        // Simulate valid login steps here; this should include entering username and password and clicking login
        // Example:
        // WebElement usernameField = driver.findElement(By.id("username"));
        // usernameField.sendKeys("validUser");
        // WebElement passwordField = driver.findElement(By.id("password"));
        // passwordField.sendKeys("validPassword");
        // driver.findElement(By.id("loginButton")).click();

        // Assert that login was successful
        // String expectedTitle = "Dashboard";
        // String actualTitle = driver.getTitle();
        // Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testInvalidLogin() {
        // Simulate invalid login steps here
        // Example:
        // WebElement usernameField = driver.findElement(By.id("username"));
        // usernameField.sendKeys("invalidUser");
        // WebElement passwordField = driver.findElement(By.id("password"));
        // passwordField.sendKeys("invalidPassword");
        // driver.findElement(By.id("loginButton")).click();

        // Assert that error message is displayed
        // WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        // Assert.assertTrue(errorMessage.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
