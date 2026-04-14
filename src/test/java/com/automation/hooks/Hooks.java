package com.automation.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;

import java.net.URL;

public class Hooks {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Before
public void setUp() throws Exception {
           String browser = System.getProperty("browser");
           if (browser == null) browser = "chrome";
           String headless = System.getProperty("headless", "true");

        if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("-headless");
            }

            driver.set(new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    options
            ));

        } else {

            ChromeOptions options = new ChromeOptions();

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            driver.set(new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    options
            ));
        }
    }

    @After
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
