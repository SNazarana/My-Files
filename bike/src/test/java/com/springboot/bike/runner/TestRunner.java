package com.springboot.bike.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "com/springboot/bike/stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
