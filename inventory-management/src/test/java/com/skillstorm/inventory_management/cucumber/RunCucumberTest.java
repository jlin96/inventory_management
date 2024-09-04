package com.skillstorm.inventory_management.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
                glue = {"com.skillstorm.inventory_management.cucumber"},
                plugin = {"pretty",
                "html:target/cucumber-Report.html",
                "json:target/report.json"}
                )

public class RunCucumberTest extends AbstractTestNGCucumberTests {



}
