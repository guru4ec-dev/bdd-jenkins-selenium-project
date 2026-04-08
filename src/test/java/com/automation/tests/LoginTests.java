package com.automation.tests;

// Selenium imports

import org.openqa.selenium.chrome.ChromeOptions;

// TestNG imports
import org.testng.annotations.*;

// Java utility imports
import java.util.List;

// Network imports (for link validation)
import java.net.URL;
import java.net.HttpURLConnection;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // Jenkins friendly
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://testautomationcentral.com/demo/links.html");
    }

    @Test
    public void validateAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Total links found: " + links.size());

        for (WebElement link : links) {
            String linkText = link.getText();
            String url = link.getAttribute("href");

            // Skip empty or null links
            if (url == null || url.isEmpty()) {
                System.out.println("Skipping empty link: " + linkText);
                continue;
            }

            verifyLink(url, linkText);
        }
    }

    public void verifyLink(String urlString, String linkText) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode >= 200 && responseCode < 400) {
                System.out.println("✅ VALID: " + linkText + " -> " + urlString);
            } else {
                System.out.println("❌ BROKEN: " + linkText + " -> " + urlString + " | Code: " + responseCode);
            }

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + linkText + " -> " + urlString);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
