package com.automation.runners;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.automation.stepdefinitions",
    plugin = {"pretty"}
)

@Test
public class RunnerTest extends AbstractTestNGCucumberTests {

    @Test
    public void runCucumber() {
        System.out.println("Triggering Cucumber execution");
    }
}