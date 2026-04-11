package com.automation.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {

    WebDriver driver;

    @Given("user is on login page")
    public void openLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

    }

    @When("user enters username and password")
    public void enterCredentials() {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("user should land on dashboard")
    public void verifyDashboard() {
        System.out.println("Login successful");
        driver.quit();
    }
}