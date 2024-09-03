package com.skillstorm.inventory_management.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

//@Suite
//@IncludeEngines("cucumber")
//@CucumberOptions(features = "src/test/resources/features",glue = {"cucumber"},plugin = {"pretty","html"})
@CucumberOptions(features = "src/test/resources/features",
                glue = {"com.skillstorm.inventory_management.cucumber"},
                plugin = {"pretty",
                "html:target/cucumber-Report.html",
                "json:target/report.json"}
                )
//tags="@product"
//@SelectClasspathResource("features")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest extends AbstractTestNGCucumberTests {



}
