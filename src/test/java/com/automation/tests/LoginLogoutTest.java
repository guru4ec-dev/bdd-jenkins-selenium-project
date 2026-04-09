package com.automation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginLogoutTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void testLoginAndLogout() {

        // Enter username
        driver.findElement(By.id("username")).sendKeys("student");

        // Enter password
        driver.findElement(By.id("password")).sendKeys("Password123");

        // Click login button
        driver.findElement(By.id("submit")).click();

        // Validate successful login (URL or message)
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("logged-in-successfully"),
                "Login failed!");

        // Validate success message
        String successMsg = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(successMsg, "Logged In Successfully");

        // Click logout button
        driver.findElement(By.linkText("Log out")).click();

        // Validate logout (back to login page)
        Assert.assertTrue(driver.getCurrentUrl().contains("practice-test-login"),
                "Logout failed!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}