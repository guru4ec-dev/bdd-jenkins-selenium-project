package com.automation.runner;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.automation.stepdefinitions",
    plugin = {
    "pretty",
    "html:target/cucumber-report.html",
    "json:target/cucumber.json",
    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    }
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {

    @Test
    public void runCucumber() {
        System.out.println("Triggering Cucumber execution");
    }
}